package com.example.multipledatabaseconnection.fileDetails.dto;

import com.example.multipledatabaseconnection.timeStamps.TimeStamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FileDetailsRequestDto extends TimeStamp  {
    //to save data
    private Long fileDetailsId;
    private String filePath;
}
