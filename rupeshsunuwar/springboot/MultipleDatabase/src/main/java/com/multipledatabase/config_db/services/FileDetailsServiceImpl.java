package com.multipledatabase.config_db.services;


import com.multipledatabase.config_db.dto.FileDetailsDto;
import com.multipledatabase.config_db.entity.FileDetails;
import com.multipledatabase.config_db.enums.FileDetailsEnum;
import com.multipledatabase.config_db.repository.FileDetailsRepository;
import com.multipledatabase.inventory_db.entity.Product;
import com.multipledatabase.inventory_db.enums.ProductEnum;
import com.multipledatabase.inventory_db.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


@Service
@EnableScheduling
public class FileDetailsServiceImpl implements FileDetailsService {

    private static int completedCount = 0;


    private static final String COMMA_DELIMITER = ",";
    @Autowired
    FileDetailsRepository fileDetailsRepository;
    @Autowired
    ProductRepository productRepository;


    public static FileDetails createFileDetails(FileDetailsDto fileDetailsDto) {

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

        List<FileDetails> fileDetailsList = fileDetailsRepository.findAll();
        System.out.println(fileDetailsList);
        for (int i = completedCount; i < fileDetailsList.size(); i++) {
            System.out.println(i);
            try {
                startProcessingFileDetails(fileDetailsList.get(i));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }


    public void startProcessingFileDetails(FileDetails filesDetails) throws Exception {

        filesDetails.setStatus(FileDetailsEnum.PROCESSING);
        fileDetailsRepository.save(filesDetails);

        List<Product> productList = readDataFromCsv(filesDetails);
        addAllProduct(productList);

        filesDetails.setStatus((FileDetailsEnum.COMPLETE));
        fileDetailsRepository.save(filesDetails);
        completedCount++;
    }


    public List<Product> readDataFromCsv(FileDetails filesDetails) throws Exception {
        int successCount = 0;
        int failureCount = 0;
        List<Product> allProduct = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\" + filesDetails.getFile_Path()))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);


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

                if (checkProduct(product1, allProduct)) {
                    successCount++;
                    productList.add(product1);
                } else {
                    failureCount++;

                }
                filesDetails.setFailure_Count(failureCount);
                filesDetails.setSuccess_Count(successCount);
                fileDetailsRepository.save(filesDetails);

            }


        }
        return productList;
    }


    public void addAllProduct(List<Product> productList) {


        productRepository.saveAll(productList);

    }


    public boolean saveFileDetails(FileDetailsDto fileDetails) {

        fileDetailsRepository.save(createFileDetails(fileDetails));

        return true;
    }


    public static boolean checkProduct(Product product, List<Product> productList) {

        boolean flag = true;

        for (Product product1 : productList) {

            if (product.getCode().equals(product1.getCode()) || product.getStatus().equals(ProductEnum.ACTIVE)) {
                flag = false;
                break;
            }
        }
        return flag;


    }


}



