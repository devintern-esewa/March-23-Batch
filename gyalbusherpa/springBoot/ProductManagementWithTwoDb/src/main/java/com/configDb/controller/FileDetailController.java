package com.configDb.controller;

import com.configDb.dto.FileRequestDto;
import com.configDb.dto.FileResponseDto;
import com.configDb.service.FileDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDetailController {

    Logger logger = LoggerFactory.getLogger(FileDetailController.class);

    private final FileDetailService fileDetailService;

    public FileDetailController(FileDetailService fileDetailService) {
        this.fileDetailService = fileDetailService;
    }

    @PostMapping("/fileDetail")
    public ResponseEntity<FileResponseDto> saveFileDetail(@RequestBody FileRequestDto fileRequestDto){
        logger.info("File detail saved successfully");
        return new ResponseEntity<FileResponseDto>(fileDetailService.saveFileDetail(fileRequestDto), HttpStatus.CREATED);
    }
}