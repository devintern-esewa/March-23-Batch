package com.multipledatabase.configdb.services;


import com.multipledatabase.configdb.dto.FileDetailsDto;
import com.multipledatabase.configdb.entity.FileDetails;
import com.multipledatabase.inventorydb.entity.Product;

import java.util.List;

public interface FileDetailsService {


    public boolean getAllFileDetails();

    public void startProcessingFileDetails(FileDetails filesDetails) throws Exception;

    public List<Product> readDataFromCsv(FileDetails filesDetails) throws Exception;

    public boolean addFileDetails(FileDetailsDto fileDetails);


}
