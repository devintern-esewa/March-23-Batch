package com.example.multipledatabaseconnection.fileDetails.controller;

import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsRequestDto;
import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsResponseDto;
import com.example.multipledatabaseconnection.fileDetails.model.FileDetails;
import com.example.multipledatabaseconnection.fileDetails.service.FileDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fileDetails")
public class FileDetailsController {
    @Autowired
    private FileDetailsService fileDetailsService;

    @GetMapping("/getAllFileDetails")
    public List<FileDetails> getAllFileDetails() {
        return fileDetailsService.getAllFileDetails();
    }

    @GetMapping("/getFileDetailsById/{fileDetailsId}")
    public FileDetailsResponseDto getFileDetailsById(@PathVariable("fileDetailsId") Long fileDetailsId) {
        return fileDetailsService.getFileDetailsById(fileDetailsId);
    }

    @PostMapping("/addNewFileDetails")
    public void addNewFileDetails(@RequestBody FileDetailsRequestDto fileDetailsRequestDto) {
        fileDetailsService.addNewFileDetails(fileDetailsRequestDto);
    }

    @PutMapping("/updateFileDetails")
    public void updateFileDetails(@RequestBody FileDetailsRequestDto fileDetailsRequestDto) {
        fileDetailsService.updateFileDetails(fileDetailsRequestDto);
    }


}
