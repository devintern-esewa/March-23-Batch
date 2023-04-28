package com.example.mulltipledbconnectiontask.fileDetails.service;

import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsRequestDto;
import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsResponseDto;
import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import com.example.mulltipledbconnectiontask.fileDetails.repo.FileDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileDetailsServiceImpl implements FileDetailsService{
    @Autowired
    private FileDetailsRepo fileDetailsRepo;

    @Override
    public List<FileDetails> getALLFileDetails() {
        return fileDetailsRepo.findAll();
    }

    @Override
    public FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        FileDetails fileDetails=new FileDetails();
        fileDetails.setFilePath(fileDetailsRequestDto.getFilePath());
        fileDetails.setFileStatus(FileStatus.valueOf("PENDING"));
        fileDetails.setSuccessCount(0L);
        fileDetails.setFailureCount(0L);
        return fileDetailsRepo.save(fileDetails);
    }

    @Override
    public FileDetailsResponseDto getFileDetailsById(Long fileDetailsId) {
        FileDetailsResponseDto fileDetailsResponseDto=new FileDetailsResponseDto();
        FileDetails fileDetails=fileDetailsRepo.findById(fileDetailsId).orElseThrow();
        fileDetailsResponseDto.setFilePath(fileDetails.getFilePath());
        fileDetailsResponseDto.setFileStatus(fileDetails.getFileStatus());
        return fileDetailsResponseDto;
    }
}
