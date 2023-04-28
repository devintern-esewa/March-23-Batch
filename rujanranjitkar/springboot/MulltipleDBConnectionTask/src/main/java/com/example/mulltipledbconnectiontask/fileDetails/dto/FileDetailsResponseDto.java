package com.example.mulltipledbconnectiontask.fileDetails.dto;

import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDetailsResponseDto {
    private String filePath;
    private FileStatus fileStatus;
}
