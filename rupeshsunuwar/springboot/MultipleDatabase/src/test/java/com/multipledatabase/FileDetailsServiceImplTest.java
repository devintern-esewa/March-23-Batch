package com.multipledatabase;

import com.multipledatabase.configdb.dto.FileDetailsDto;
import com.multipledatabase.configdb.entity.FileDetails;
import com.multipledatabase.configdb.enums.FileDetailsEnum;
import com.multipledatabase.configdb.repository.FileDetailsRepository;
import com.multipledatabase.configdb.services.FileDetailsServiceImpl;
import com.multipledatabase.inventorydb.dto.ProductDto;
import com.multipledatabase.inventorydb.entity.Product;
import com.multipledatabase.inventorydb.services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class FileDetailsServiceImplTest {


    @Mock
    private FileDetailsRepository fileDetailsRepository;

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private FileDetailsServiceImpl fileDetailsService;

    public FileDetailsServiceImplTest() {

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void convertToFileDetails_WhenDtoIsPassed_ThenReturnFileDetails() {
        FileDetailsDto fileDetailsDto = new FileDetailsDto("Product.csv");
        FileDetails fileDetails1 = new FileDetails();
        fileDetails1.setFile_Path(fileDetailsDto.getFile_Path());
        fileDetails1.setStatus(FileDetailsEnum.PENDING);
        fileDetails1.setFailure_Count(0);
        fileDetails1.setSuccess_Count(0);

        assertEquals(fileDetails1.getFile_Path(), fileDetailsService.convertToFileDetails(fileDetailsDto).getFile_Path());
    }

    @Test
    public void getAllFileDetails_WhenFileDetailsWithPendingStatusExist_ThenReturnTrue() {
        List<FileDetails> fileDetailsList = new ArrayList<>();
        FileDetails fileDetails1 = new FileDetails();
        fileDetails1.setFile_Path("one.csv");
        fileDetails1.setStatus(FileDetailsEnum.PENDING);
        fileDetails1.setFailure_Count(0);
        fileDetails1.setSuccess_Count(0);
        fileDetailsList.add(fileDetails1);

        List<ProductDto> productList = new ArrayList<>();
        productList.add(new ProductDto());
        when(fileDetailsRepository.findByStatus(FileDetailsEnum.PENDING)).thenReturn(fileDetailsList);
        when(productService.getAllProduct()).thenReturn(productList);
        assertTrue(fileDetailsService.getAllFileDetails());
    }


    @Test
    public void getAllFileDetails_WhenFileDetailsWithPendingStatusDoesntExist_ThenReturnAllFileDetails() {
        List<FileDetails> fileDetailsList = new ArrayList<>();


        List<ProductDto> productList = new ArrayList<>();
        productList.add(new ProductDto());
        when(fileDetailsRepository.findByStatus(FileDetailsEnum.PENDING)).thenReturn(fileDetailsList);
        when(productService.getAllProduct()).thenReturn(productList);
        assertFalse(fileDetailsService.getAllFileDetails());
    }

    @Test
    public void readDataFromCsv_WhenFileDetailsWhichFilePathExist_ThenReturnProductList() throws Exception {

        List<Product> productList = new ArrayList<>();
        FileDetails fileDetails1 = new FileDetails();
        fileDetails1.setFile_Path("one.csv");
        fileDetails1.setStatus(FileDetailsEnum.PENDING);
        fileDetails1.setFailure_Count(0);
        fileDetails1.setSuccess_Count(0);

        assertEquals(productList.size(), fileDetailsService.readDataFromCsv(fileDetails1).size());

    }

    @Test
    public void startProcessingFileDetailsTest() {
        FileDetails fileDetails1 = new FileDetails();
        fileDetails1.setFile_Path("one.csv");
        fileDetails1.setStatus(FileDetailsEnum.PENDING);
        fileDetails1.setFailure_Count(0);
        fileDetails1.setSuccess_Count(0);

        try {
            fileDetailsService.startProcessingFileDetails(fileDetails1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        when(fileDetailsRepository.save(fileDetails1)).thenReturn(fileDetails1);
        verify(fileDetailsRepository, times(3)).save(fileDetails1);
    }


    @Test
    public void addFileDetails_ThenReturnTrue() {
        FileDetailsDto fileDetailsDto = new FileDetailsDto("one.csv");

        assertTrue(fileDetailsService.addFileDetails(fileDetailsDto));
    }


}
