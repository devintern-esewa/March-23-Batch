package com.example.mulltipledbconnectiontask.fileDetails.service;

import com.example.mulltipledbconnectiontask.exception.IdDoesNotExistsException;
import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsRequestDto;
import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsResponseDto;
import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import com.example.mulltipledbconnectiontask.fileDetails.repo.FileDetailsRepo;
import com.example.mulltipledbconnectiontask.inventory.model.Product;
import com.example.mulltipledbconnectiontask.inventory.repo.ProductRepo;
import com.example.mulltipledbconnectiontask.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileDetailsServiceImpl implements FileDetailsService {
    @Autowired
    private FileDetailsRepo fileDetailsRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<FileDetails> getALLFileDetails() {
        return fileDetailsRepo.findAll();
    }

    @Override
    public FileDetailsResponseDto getFileDetailsById(Long fileDetailsId) {
        FileDetailsResponseDto fileDetailsResponseDto = new FileDetailsResponseDto();
        FileDetails fileDetails = fileDetailsRepo.findById(fileDetailsId).orElseThrow(() -> new IdDoesNotExistsException("File with id " + fileDetailsId + " does not exists"));
        fileDetailsResponseDto.setFilePath(fileDetails.getFilePath());
        fileDetailsResponseDto.setFileStatus(fileDetails.getFileStatus());
        return fileDetailsResponseDto;
    }

    @Override
    public FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        FileDetails fileDetails = new FileDetails();
        fileDetails.setFilePath(fileDetailsRequestDto.getFilePath());
        fileDetails.setFileStatus(FileStatus.PENDING);
        fileDetails.setSuccessCount(0L);
        fileDetails.setFailureCount(0L);
        return fileDetailsRepo.save(fileDetails);
    }

    @Scheduled(cron = "*/30 * * * * *") // execute after 1 min of server start
    public void readFile() {
        List<FileDetails> fileDetails = fileDetailsRepo.findByFileStatus(FileStatus.PENDING);
        for (FileDetails file : fileDetails) {
            file.setFileStatus(FileStatus.PROCESSING);
            fileDetailsRepo.save(file);

            List<Product> products = productService.readProductDetailsFromFile(file.getFilePath());
            List<Product> successProducts = productService.countSuccessFailureBeforeSavingProducts(products, file.getFilePath());
            productRepo.saveAll(successProducts);

            // created a new file to set status to complete
            FileDetails updatedFile = fileDetailsRepo.findById(file.getFileDetailsId()).orElseThrow();
            updatedFile.setFileStatus(FileStatus.COMPLETE);
            fileDetailsRepo.save(updatedFile);
        }
    }
}
