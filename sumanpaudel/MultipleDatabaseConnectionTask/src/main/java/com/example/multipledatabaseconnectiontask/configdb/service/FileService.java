package com.example.multipledatabaseconnectiontask.configdb.service;

import com.example.multipledatabaseconnectiontask.configdb.dto.FileRequestDto;
import com.example.multipledatabaseconnectiontask.configdb.dto.FileResponseDto;

public interface FileService {
    FileResponseDto saveFileDetails(FileRequestDto fileRequestDto);
}
