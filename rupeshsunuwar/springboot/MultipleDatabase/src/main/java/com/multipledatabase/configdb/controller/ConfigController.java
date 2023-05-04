package com.multipledatabase.configdb.controller;


import com.multipledatabase.configdb.dto.FileDetailsDto;
import com.multipledatabase.configdb.services.FileDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {


    @Autowired
    private FileDetailsServiceImpl fileDetailsService;



    @PostMapping(value = "/file")
    public boolean saveFileDetails(@RequestBody FileDetailsDto fileDetails){

        return fileDetailsService.addFileDetails(fileDetails);
    }


}
