package com.example.mulltipledbconnectiontask.fileDetails.dto;

import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FileDetailsResponseDto implements Serializable {
    private String filePath;
    private FileStatus fileStatus;
}
