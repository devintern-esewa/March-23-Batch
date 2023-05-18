package com.bismdhr.bankmgmtsystem.controller;

import com.bismdhr.bankmgmtsystem.dto.InsuranceUpdateDto;
import com.bismdhr.bankmgmtsystem.dto.UserUpdateDto;
import com.bismdhr.bankmgmtsystem.model.Insurance;
import com.bismdhr.bankmgmtsystem.model.User;
import com.bismdhr.bankmgmtsystem.service.InsuranceService;
import com.bismdhr.bankmgmtsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @GetMapping
    public List<Insurance> getAll(){
        return insuranceService.getAllInsurances();
    }

    @GetMapping("/{id}")
    public Insurance findById(@PathVariable int id){
        return insuranceService.findById(id);
    }

    @PostMapping
    public Insurance addInsurance( @RequestBody @Valid Insurance insurance){
        return insuranceService.addInsurance(insurance);
    }

    @PutMapping
    public ResponseEntity<Insurance> updateInsurance (@RequestBody @Valid InsuranceUpdateDto insurance){
        Insurance updatedInsurance = insuranceService.updateInsurance(insurance);
        return new ResponseEntity<>(updatedInsurance, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteInsurance(@PathVariable int id){
        return insuranceService.deleteInsurance(id);
    }
}
