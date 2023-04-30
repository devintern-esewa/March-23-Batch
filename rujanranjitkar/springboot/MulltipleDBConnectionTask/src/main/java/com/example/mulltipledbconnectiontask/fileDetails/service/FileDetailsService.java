package com.example.mulltipledbconnectiontask.fileDetails.service;

import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsRequestDto;
import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsResponseDto;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;

import java.util.List;

public interface FileDetailsService {
    List<FileDetails> getALLFileDetails();

    FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto);

    FileDetailsResponseDto getFileDetailsById(Long fileDetailsId);

}
