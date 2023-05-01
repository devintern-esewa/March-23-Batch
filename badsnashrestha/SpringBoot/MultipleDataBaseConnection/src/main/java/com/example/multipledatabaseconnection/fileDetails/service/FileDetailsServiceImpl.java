package com.example.multipledatabaseconnection.fileDetails.service;


import com.example.multipledatabaseconnection.exception.FileNotFoundException;
import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsRequestDto;
import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsResponseDto;
import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.fileDetails.model.FileDetails;
import com.example.multipledatabaseconnection.fileDetails.repo.FileDetailsRepo;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import com.example.multipledatabaseconnection.productDetails.service.ProductDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileDetailsServiceImpl implements FileDetailsService {
    Logger logger = LoggerFactory.getLogger(FileDetailsServiceImpl.class);
    @Autowired
    private FileDetailsRepo fileDetailsRepo;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Override
    public List<FileDetails> getAllFileDetails() {
        logger.info("Getting all the details of file");
        return fileDetailsRepo.findAll();
    }

    @Override
    public FileDetailsResponseDto getFileDetailsById(Long fileDetailsId) {
        FileDetailsResponseDto fileDetailsResponseDto = new FileDetailsResponseDto();
        FileDetails fileDetails = fileDetailsRepo.findById(fileDetailsId).orElseThrow(() -> new FileNotFoundException("File with " + fileDetailsId + " doesn't exists"));
        fileDetailsResponseDto.setFilePath(fileDetails.getFilePath());
        fileDetailsResponseDto.setFileStatus(fileDetails.getFileStatus());
        logger.info("Getting details of file using fileDetailsId");
        return fileDetailsResponseDto;
    }

    @Override
    public FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        FileDetails fileDetails = new FileDetails();
        fileDetails.setFilePath(fileDetailsRequestDto.getFilePath());
        fileDetails.setFileStatus(FileStatus.PENDING);
        fileDetails.setFailureCount(0);
        fileDetails.setSuccessCount(0);
        logger.info("Adding New FileDetails");
        return fileDetailsRepo.save(fileDetails);
    }

    @Override
    public void updateFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        Optional<FileDetails> fileDetails = fileDetailsRepo.findById(fileDetailsRequestDto.getFileDetailsId());
        FileDetails fileDetail = fileDetails.get();
        fileDetail.setFilePath(fileDetailsRequestDto.getFilePath());
        logger.info("Updating the FileDetails ");
        fileDetailsRepo.save(fileDetail);
    }

    //to schedule the execution of method after 1 min of server start
    @Scheduled(cron = "0 */1 * * * * ")
    @Override
    public void readFile() throws IOException {
        List<FileDetails> fileDetails = fileDetailsRepo.findByFileStatus(FileStatus.PENDING);
        for (FileDetails fileDetail : fileDetails) {
            fileDetail.setFileStatus(FileStatus.PROCESSING);
            fileDetailsRepo.save(fileDetail);

            logger.info("Sending filePath to readCsvInsertIntoProductsDetails() of productDetailsService");
            List<ProductDetails> productDetails = productDetailsService.readCsvInsertIntoProductDetails(fileDetail.getFilePath());

            logger.info("Sending productDetails to processingProduct() of productDetailsService");
            List<ProductDetails> productDetails1 = productDetailsService.processingProduct(productDetails, fileDetail.getFilePath());

            logger.info("Calling addNewProduct() of productDetailsService ");
            productDetailsService.addNewProduct(productDetails1);

            logger.info("Creating new transaction to update FileStatus");
            FileDetails fileDetails1 = fileDetailsRepo.findById(fileDetail.getFileDetailsId()).orElseThrow(() -> new FileNotFoundException("File with " + fileDetail.getFileDetailsId() + " doesn't exists"));
            fileDetails1.setFileStatus(FileStatus.COMPLETE);
            fileDetailsRepo.save(fileDetails1);
        }
    }


}
