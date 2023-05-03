package com.example.multipledbconnection.productDetails.controller;

import com.example.multipledbconnection.fileDetails.dto.DtoFDResponse;
import com.example.multipledbconnection.productDetails.modelProd.ProductDetails;
import com.example.multipledbconnection.productDetails.service.ServicePD;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ControllerPD {

    public final ServicePD servicePD;


    public ControllerPD(ServicePD servicePD) {
        this.servicePD = servicePD;

    }

    @GetMapping("/getAllproductDetails")
    public List<ProductDetails> getAllProduct(){
        return servicePD.getAllProduct();
    }



}
