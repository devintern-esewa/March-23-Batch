package com.example.multipledatabaseconnection.fileDetails.dto;

import com.example.multipledatabaseconnection.timeStamps.TimeStamp;
import lombok.Data;

@Data
public class FileDetailsRequestDto extends TimeStamp {
    private Long fileDetailsId;
    //to save data
    private String filePath;
}
