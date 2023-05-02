package com.multipledatabase.config_db.services;


import com.multipledatabase.config_db.dto.FileDetailsDto;
import com.multipledatabase.config_db.entity.FileDetails;
import com.multipledatabase.config_db.enums.FileDetailsEnum;
import com.multipledatabase.config_db.repository.FileDetailsRepository;
import com.multipledatabase.inventory_db.entity.Product;
import com.multipledatabase.inventory_db.enums.ProductEnum;
import com.multipledatabase.inventory_db.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
@EnableScheduling
public class FileDetailsServiceImpl implements FileDetailsService {


    private static final String COMMA_DELIMITER = ",";
    @Autowired
    FileDetailsRepository fileDetailsRepository;
    @Autowired
    ProductRepository productRepository;



    public static FileDetails createFileDetails(FileDetailsDto fileDetailsDto){


        String filePath=fileDetailsDto.getFile_Path();
        FileDetails fileDetails1=new FileDetails();
        fileDetails1.setFile_Path(fileDetailsDto.getFile_Path());
        fileDetails1.setStatus(FileDetailsEnum.PENDING);
        fileDetails1.setFailure_Count(0);
        fileDetails1.setSuccess_Count(0);
        return fileDetails1;
    }

    @Override
    //To schedule a method to be executed once and then every 1 minute using cron expression
//    @Scheduled(cron = "0 */1 * * * ?")
    public void getAllFileDetails() {

        List<FileDetails> filesDetails = new ArrayList<>();

        fileDetailsRepository.findAll().forEach(filesDetails::add);
        System.out.println(filesDetails);

        try {
            updateFileDetails(filesDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void updateFileDetails(List<FileDetails> filesDetails) throws Exception {

        List<Product> product = new ArrayList<>();

        for (FileDetails file : filesDetails) {

            try (BufferedReader br = new BufferedReader(new FileReader(file.getFile_Path()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(COMMA_DELIMITER);

                    String name = values[0];
                    String code = values[1];
                    String qty = values[3];
                    String price = values[4];


                    product.add(new Product(name, code, ProductEnum.ACTIVE, Integer.parseInt(qty), Integer.parseInt(price)));

                }

                productRepository.saveAll(product);


            }


        }

    }


    public boolean saveFileDetails(FileDetailsDto fileDetails) {

        fileDetailsRepository.save(createFileDetails(fileDetails));

        return true;
    }


}



