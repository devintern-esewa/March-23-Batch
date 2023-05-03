package com.example.multipledbconnection.fileDetails.controller;

import com.example.multipledbconnection.fileDetails.dto.DtoFDRequest;
import com.example.multipledbconnection.fileDetails.dto.DtoFDResponse;
import com.example.multipledbconnection.fileDetails.model.FileDetails;
import com.example.multipledbconnection.fileDetails.service.FDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")

public class FDController {

    public final FDService fdService;


    public FDController(FDService fdService) {
        this.fdService = fdService;
    }


    @GetMapping("/getAll")
    public List<FileDetails> getAllFD() {
        return fdService.getAllFD();

    }

    @GetMapping("/getById/{fileId}")
    public DtoFDResponse getFDByID(@PathVariable("fileId") Long fileId) {
        return fdService.getFDByID(fileId);
    }


    @PostMapping("/addFD")
    public void addFD(@RequestBody DtoFDRequest dtoFDRequest) {
        System.out.println(dtoFDRequest);
         fdService.addFD(dtoFDRequest);
    }

    @PutMapping("/updateFD")
    public FileDetails updateFD(@RequestBody DtoFDRequest dtoFDRequest, Long fileId ){
     return fdService.updateFD(dtoFDRequest,fileId);
    }

}
