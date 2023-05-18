package com.example.multipledatabaseconnection.fileDetails.service;


import com.example.multipledatabaseconnection.exception.FileNotFoundException;
import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsRequestDto;
import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsResponseDto;
import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.fileDetails.model.FileDetails;
import com.example.multipledatabaseconnection.fileDetails.repo.FileDetailsRepo;
import com.example.multipledatabaseconnection.productDetails.model.ProductDetails;
import com.example.multipledatabaseconnection.productDetails.service.ProductDetailsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDetailsServiceImpl implements FileDetailsService {
    Logger logger = LoggerFactory.getLogger(FileDetailsServiceImpl.class);
    private final FileDetailsRepo fileDetailsRepo;

    private final ProductDetailsService productDetailsService;

    @Override
    public List<FileDetails> getAllFileDetails() {
        logger.info("Getting all the details of file");
        return fileDetailsRepo.findAll();
    }

    @Override
    @CachePut(cacheNames = "files", key = "#fileDetailsId")

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
        logger.info("File Status set to Pending");
        fileDetails.setFileStatus(FileStatus.PENDING);
        fileDetails.setFailureCount(0);
        fileDetails.setSuccessCount(0);

        logger.info("Adding New FileDetails");
        return fileDetailsRepo.save(fileDetails);
    }

    @Override
    public void updateFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        FileDetails fileDetail = fileDetailsRepo.findById(fileDetailsRequestDto.getFileDetailsId()).orElseThrow(()->new FileNotFoundException("File not found"));

        fileDetail.setFilePath(fileDetailsRequestDto.getFilePath());

        logger.info("Updating the FileDetails ");
        fileDetailsRepo.save(fileDetail);
    }

    //to schedule the execution of method after 1 min of server start
    @Scheduled(cron = "*/30 * * * * * ")
    public void readFile() throws IOException {
        List<FileDetails> fileDetails = fileDetailsRepo.findByFileStatus(FileStatus.PENDING);
        for (FileDetails fileDetail : fileDetails) {

            logger.info("File Status set to Processing");
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

            logger.info("Inserting updated fileDetails into fileDetails table");
            fileDetailsRepo.save(fileDetails1);
        }
    }


}
