package com.example.multipledatabaseconnection.fileDetails.dto;

import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class FileDetailsResponseDto implements Serializable {
    private String filePath;
    private FileStatus fileStatus;


}
