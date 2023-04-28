package com.example.multipledatabaseconnection.FileDetails.service;


import com.example.multipledatabaseconnection.FileDetails.dto.FileDetailsRequestDto;
import com.example.multipledatabaseconnection.FileDetails.dto.FileDetailsResponseDto;
import com.example.multipledatabaseconnection.FileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.FileDetails.model.FileDetails;
import com.example.multipledatabaseconnection.FileDetails.model.OtherInfo;
import com.example.multipledatabaseconnection.FileDetails.repo.FileDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class FileDetailsServiceImpl implements FileDetailsService{
    @Autowired
   private FileDetailsRepo fileDetailsRepo;

    @Override
    public List<FileDetails> getAllFileDetails() {
        return fileDetailsRepo.findAll();
    }

    @Override
    public FileDetailsResponseDto getFileDetailsById(Long fileDetailsId) {
        FileDetailsResponseDto fileDetailsResponseDto=new FileDetailsResponseDto();
        FileDetails fileDetails=fileDetailsRepo.findById(fileDetailsId).orElseThrow();
        fileDetailsResponseDto.setFilePath(fileDetails.getFilePath());
       fileDetailsResponseDto.setFileStatus(fileDetails.getFileStatus());
        return fileDetailsResponseDto;
    }

    @Override
    public FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        FileDetails fileDetails= new FileDetails();
        fileDetails.setFilePath(fileDetailsRequestDto.getFilePath());
        fileDetails.setFileStatus(FileStatus.PENDING);
        fileDetails.setFailureCount(0L);
        fileDetails.setSuccessCount(0L);

        return fileDetailsRepo.save(fileDetails);
    }

    @Override
    public void updateFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        Optional<FileDetails> fileDetails=fileDetailsRepo.findById(fileDetailsRequestDto.getFileDetailsId());
        FileDetails fileDetails1=fileDetails.get();
        fileDetails1.setFilePath(fileDetailsRequestDto.getFilePath());
        fileDetailsRepo.save(fileDetails1);
    }
}
