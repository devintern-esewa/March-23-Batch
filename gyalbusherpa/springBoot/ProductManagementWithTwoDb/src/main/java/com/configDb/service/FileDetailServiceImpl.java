package com.configDb.service;

import com.configDb.dto.FileRequestDto;
import com.configDb.dto.FileResponseDto;
import com.configDb.enums.FileStatusEnum;
import com.configDb.model.FileDetail;
import com.configDb.repository.FileDetailRepository;
import com.inventoryDb.model.Product;
import com.inventoryDb.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileDetailServiceImpl implements FileDetailService {

    Logger logger = LoggerFactory.getLogger(FileDetailServiceImpl.class);
    private final FileDetailRepository fileDetailRepository;
    private final ProductService productService;

    public FileDetailServiceImpl(FileDetailRepository fileDetailRepository, ProductService productService) {
        this.fileDetailRepository = fileDetailRepository;
        this.productService = productService;
    }

    @Override
    public FileResponseDto saveFileDetail(FileRequestDto fileRequestDto) {
        FileDetail fileDetail = new FileDetail();

        fileDetail.setFileStatus(FileStatusEnum.PENDING);
        fileDetail.setFilePath(fileRequestDto.getFilePath());

        fileDetailRepository.save(fileDetail);

        FileResponseDto fileResponseDto = new FileResponseDto();
        fileResponseDto.setFilePath(fileRequestDto.getFilePath());
        fileResponseDto.setStatus(fileDetail.getFileStatus());

        return fileResponseDto;
    }

    @Scheduled(cron = "*/10 * * * * *")
    void set() {
        List<FileDetail> fileDetails = fileDetailRepository.findByFileStatus(FileStatusEnum.PENDING);
        for (FileDetail updateFileStatus : fileDetails) {

            updateFileStatus.setFileStatus(FileStatusEnum.PROCESSING);
            fileDetailRepository.save(updateFileStatus);


            // Converts products detail present in csv file to List of Product type
            List<Product> products = productService.convertCsvDataInFilePathToProduct(updateFileStatus.getFilePath());

            // Success and failure count logic
            // Returns only unique list of products to be saved.
            List<Product> processedProduct = productService.processProduct(products,updateFileStatus.getFilePath());

            logger.info("file processing");
            productService.saveProduct(processedProduct);

            List<FileDetail> fileDetails1 = fileDetailRepository.findByFileStatus(FileStatusEnum.PROCESSING);
            for (FileDetail files : fileDetails1) {
                files.setFileStatus(FileStatusEnum.COMPLETE);
                fileDetailRepository.save(files);
            }
            logger.info("file processing completed");
        }

    }
}
