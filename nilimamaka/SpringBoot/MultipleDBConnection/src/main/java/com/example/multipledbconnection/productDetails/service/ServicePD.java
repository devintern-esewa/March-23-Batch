package com.example.multipledbconnection.productDetails.service;

import com.example.multipledbconnection.fileDetails.dto.DtoFDResponse;
import com.example.multipledbconnection.productDetails.modelProd.ProductDetails;

import java.util.List;

public interface ServicePD {


    List<ProductDetails> getAllProduct();
}
