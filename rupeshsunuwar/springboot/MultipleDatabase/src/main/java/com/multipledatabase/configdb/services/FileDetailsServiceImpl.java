package com.multipledatabase.configdb.services;


import com.multipledatabase.configdb.dto.FileDetailsDto;
import com.multipledatabase.configdb.entity.FileDetails;
import com.multipledatabase.configdb.enums.FileDetailsEnum;
import com.multipledatabase.configdb.repository.FileDetailsRepository;
import com.multipledatabase.security.Cipher;
import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;
import com.multipledatabase.inventorydb.enums.ProductEnum;
import com.multipledatabase.inventorydb.services.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static com.multipledatabase.security.Cipher.encryption;


@Service
@Slf4j
@EnableScheduling
@EnableCaching
public class FileDetailsServiceImpl implements FileDetailsService {

    private Logger logger = LoggerFactory.getLogger(FileDetailsServiceImpl.class);

    private static int completedCount = 0;



    @Autowired
    FileDetailsRepository fileDetailsRepository;
    @Autowired
    ProductServiceImpl productService;





    public FileDetails createFileDetails(FileDetailsDto fileDetailsDto) {

        FileDetails fileDetails1 = new FileDetails();
        fileDetails1.setFile_Path(fileDetailsDto.getFile_Path());
        fileDetails1.setStatus(FileDetailsEnum.PENDING);
        fileDetails1.setFailure_Count(0);
        fileDetails1.setSuccess_Count(0);
        return fileDetails1;
    }

    @Override
    //To schedule a method to be executed once and then every 1 minute using cron expression
    @Scheduled(cron = "0 */1 * * * ?")
    public void getAllFileDetails() {

        logger.info("Getting all FileDetails.");
        List<FileDetails> fileDetailsList = fileDetailsRepository.findAll();
        System.out.println(fileDetailsList);
        for (int i = completedCount; i < fileDetailsList.size(); i++) {
            System.out.println(i);
            try {
                logger.info("Starting processing file Details");
                startProcessingFileDetails(fileDetailsList.get(i));
                logger.info("Completed processing file Details");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }


    public void startProcessingFileDetails(FileDetails filesDetails) throws Exception {

        filesDetails.setStatus(FileDetailsEnum.PROCESSING);
        logger.info("Saving the status of FileDetails");
        fileDetailsRepository.save(filesDetails);
        logger.info("Saved the status of FileDetails");

        logger.info("Reading data from CSV File.");
        List<Product> productList = readDataFromCsv(filesDetails);
        logger.info("Adding return product List read from csv file in database.");
        productService.addAllProduct(productList);
        logger.info("Added return product in database");

        filesDetails.setStatus((FileDetailsEnum.COMPLETE));
        logger.info("saving the status of FileDetails");
        fileDetailsRepository.save(filesDetails);
        logger.info("saving the status of FileDetails");
        completedCount++;
    }


    public List<Product> readDataFromCsv(FileDetails filesDetails) throws Exception {



        Cipher obj=new Cipher();
        logger.info("Calling the getSecretKey to get the secret key from Test class");
        logger.info("Received Secret key.");
        int successCount = 0;
        int failureCount = 0;
        logger.info("Getting all the product from the database");
        List<ProductDto> allProduct = productService.getAllProduct();
        logger.info("Received all the product from database");
        List<Product> productList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\" + filesDetails.getFile_Path()))) {


            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");


                String name = values[0];
                String code = values[1];
                double qty = Double.parseDouble(values[3]);
                double price = Double.parseDouble(values[4]);
                System.out.println(name + code + qty + price);
                Product product1 = new Product();
                product1.setName(name);
                product1.setStatus(ProductEnum.ACTIVE);
                product1.setCode(code);
                product1.setQty(qty);
                product1.setPrice(price);
                logger.info("Calling method checkProduct");
                if (productService.checkProductStatus(product1, allProduct)) {
                    logger.info("product read from csv is not present in database");
                    successCount++;
                    productList.add(product1);
                } else {
                    logger.info("product read from csv is present in database");
                    failureCount++;

                }
            }
            filesDetails.setFailure_Count(failureCount);
            filesDetails.setSuccess_Count(successCount);
            logger.info("Setting the success and failure count.");
            fileDetailsRepository.save(filesDetails);
            logger.info("Saved the success and failure count.");


        }
        return productList;
    }


    public boolean addFileDetails(FileDetailsDto fileDetails) {
        logger.info("Saving the FilesDetails in database");
        fileDetailsRepository.save(createFileDetails(fileDetails));


        logger.info("Saved the FilesDetails in database");

        return true;
    }


}



