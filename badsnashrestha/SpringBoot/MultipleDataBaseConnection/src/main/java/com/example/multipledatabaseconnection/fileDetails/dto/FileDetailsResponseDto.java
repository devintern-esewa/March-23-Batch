package com.example.multipledatabaseconnection.fileDetails.dto;

import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDetailsResponseDto {
    private String filePath;
    private FileStatus fileStatus;
}
