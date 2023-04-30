package com.example.multipledatabaseconnection.fileDetails.dto;

import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import lombok.Data;

@Data
public class FileDetailsResponseDto {
    private String filePath;
    private FileStatus fileStatus;
}
