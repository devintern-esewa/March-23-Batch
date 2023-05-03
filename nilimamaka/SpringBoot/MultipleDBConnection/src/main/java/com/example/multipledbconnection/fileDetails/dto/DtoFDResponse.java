package com.example.multipledbconnection.fileDetails.dto;

import com.example.multipledbconnection.fileDetails.enums.FDStatus;
import lombok.Data;

@Data
public class DtoFDResponse {
    private String filePath;
    private FDStatus fdStatus;
}
