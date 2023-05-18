package com.example.mulltipledbconnectiontask.fileDetails.controller;

import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsRequestDto;
import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsResponseDto;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import com.example.mulltipledbconnectiontask.fileDetails.service.FileDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/FileDetails")
public class FileDetailsController {
    private final FileDetailsService fileDetailsService;

    public FileDetailsController(FileDetailsService fileDetailsService) {
        this.fileDetailsService = fileDetailsService;
    }

    @GetMapping("/getAllFileDetails")
    public List<FileDetails> getAllFileDetails() {
        return fileDetailsService.getALLFileDetails();
    }

    @PostMapping("/addNewFileDetails")
    public void addNewFileDetails(@RequestBody FileDetailsRequestDto fileDetailsRequestDto) {
        fileDetailsService.addNewFileDetails(fileDetailsRequestDto);
    }

    @GetMapping("/getFileDetailsById/{fileDetailsId}")
    public FileDetailsResponseDto getFileDetailsById(@PathVariable("fileDetailsId") Long fileDetailsId) {
        return fileDetailsService.getFileDetailsById(fileDetailsId);
    }
}
