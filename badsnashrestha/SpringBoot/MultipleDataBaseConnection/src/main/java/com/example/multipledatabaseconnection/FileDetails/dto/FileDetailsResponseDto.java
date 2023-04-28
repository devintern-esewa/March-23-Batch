package com.example.multipledatabaseconnection.FileDetails.dto;

import com.example.multipledatabaseconnection.FileDetails.enums.FileStatus;
import lombok.Data;

@Data
public class FileDetailsResponseDto {
    private String filePath;
    private FileStatus fileStatus;
}
