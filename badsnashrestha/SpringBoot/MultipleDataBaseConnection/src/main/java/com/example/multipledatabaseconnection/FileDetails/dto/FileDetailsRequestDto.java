package com.example.multipledatabaseconnection.FileDetails.dto;

import com.example.multipledatabaseconnection.FileDetails.model.OtherInfo;
import lombok.Data;

@Data
public class FileDetailsRequestDto extends OtherInfo {
    private Long fileDetailsId;
    //to save data
    private String filePath;
}
