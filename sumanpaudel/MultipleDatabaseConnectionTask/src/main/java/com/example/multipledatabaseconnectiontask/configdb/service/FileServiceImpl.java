package com.example.multipledatabaseconnectiontask.configdb.service;

import com.example.multipledatabaseconnectiontask.configdb.dto.FileRequestDto;
import com.example.multipledatabaseconnectiontask.configdb.dto.FileResponseDto;
import com.example.multipledatabaseconnectiontask.configdb.model.FileDetails;
import com.example.multipledatabaseconnectiontask.configdb.repo.FileDetailsRepository;
import com.example.multipledatabaseconnectiontask.configdb.statusenum.FileStatusEnum;
import com.example.multipledatabaseconnectiontask.inventorydb.model.Product;
import com.example.multipledatabaseconnectiontask.inventorydb.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


@Service
@EnableScheduling
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDetailsRepository fileDetailsRepository;
    @Autowired
    private ProductServiceImpl productService;

    @Override
    public FileResponseDto saveFileDetails(FileRequestDto fileRequestDto) {
        FileDetails fileDetails = new FileDetails();
        fileDetails.setFilepath(fileRequestDto.getFile_path());
        fileDetails.setStatus(FileStatusEnum.PENDING);
        fileDetails.setSuccess_count(0);
        fileDetails.setSuccess_count(0);
        fileDetailsRepository.save(fileDetails);

        FileResponseDto fileResponseDto = new FileResponseDto();
        fileResponseDto.setFile_path(fileDetails.getFilepath());
        fileResponseDto.setStatus(fileDetails.getStatus());

        return fileResponseDto;
    }

    @Scheduled(cron = "0 */1 * * * ?")
    void set() {
        List<FileDetails> fileDetails = fileDetailsRepository.findByStatus(FileStatusEnum.PENDING);
        for (FileDetails updateFileDetails : fileDetails) {
            updateFileDetails.setStatus(FileStatusEnum.PROCESSING);
            fileDetailsRepository.save(updateFileDetails);

            List<Product> products = productService.convertCsvFilePathDataIntoProduct(updateFileDetails.getFilepath());

            List<Product> products1=productService.processProduct(products, updateFileDetails.getFilepath());
            productService.saveAllProduct(products1);
        }
            List<FileDetails> fileDetails1 = fileDetailsRepository.findByStatus(FileStatusEnum.PROCESSING);
            for (FileDetails fileDetails2:fileDetails1){
                fileDetails2.setStatus(FileStatusEnum.COMPLETE);
                fileDetailsRepository.save(fileDetails2);
            }
        }
    }

