package com.example.multipledatabaseconnection.fileDetails.service;

import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsRequestDto;
import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsResponseDto;
import com.example.multipledatabaseconnection.fileDetails.model.FileDetails;

import java.io.IOException;
import java.util.List;

public interface FileDetailsService {

    List<FileDetails> getAllFileDetails();

    FileDetailsResponseDto getFileDetailsById(Long fileDetailsId);

    FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto);

    void updateFileDetails(FileDetailsRequestDto fileDetailsRequestDto);

    public void readFile() throws IOException;
}
