package com.example.multipledatabaseconnection.fileDetails.dto;

import com.example.multipledatabaseconnection.timeStamps.TimeStamp;
import lombok.Data;

@Data
public class FileDetailsRequestDto extends TimeStamp {
    //to save data
    private Long fileDetailsId;
    private String filePath;
}
