package com.example.multipledatabaseconnection.FileDetails.service;

import com.example.multipledatabaseconnection.FileDetails.dto.FileDetailsRequestDto;
import com.example.multipledatabaseconnection.FileDetails.dto.FileDetailsResponseDto;
import com.example.multipledatabaseconnection.FileDetails.model.FileDetails;

import java.io.IOException;
import java.util.List;

public interface FileDetailsService {

     List<FileDetails> getAllFileDetails();
     FileDetailsResponseDto getFileDetailsById(Long fileDetailsId);
     FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto);

     void updateFileDetails(FileDetailsRequestDto fileDetailsRequestDto);

     public  void readFile() throws IOException;
}
