package com.example.multipledbconnection.fileDetails.service;


import com.example.multipledbconnection.fileDetails.dto.DtoFDRequest;
import com.example.multipledbconnection.fileDetails.dto.DtoFDResponse;
import com.example.multipledbconnection.fileDetails.model.FileDetails;

import java.io.IOException;
import java.util.List;

public interface FDService  {
 List<FileDetails> getAllFD();

    FileDetails addFD(DtoFDRequest dtoFDRequest);

    DtoFDResponse getFDByID(Long fileId);

    FileDetails updateFD(DtoFDRequest dtoFDRequest, Long fileId);


    public void readFile() throws IOException;
}
