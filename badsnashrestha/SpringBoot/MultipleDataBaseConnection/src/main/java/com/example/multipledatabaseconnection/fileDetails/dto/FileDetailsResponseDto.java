package com.example.multipledatabaseconnection.fileDetails.dto;

import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class FileDetailsResponseDto {
    private String filePath;
    private FileStatus fileStatus;


}
