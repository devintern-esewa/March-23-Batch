package com.example.multipledatabaseconnection.fileDetails.controller;

import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsRequestDto;
import com.example.multipledatabaseconnection.fileDetails.dto.FileDetailsResponseDto;
import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.fileDetails.model.FileDetails;
import com.example.multipledatabaseconnection.fileDetails.service.FileDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileDetailsController.class)
class FileDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FileDetailsServiceImpl fileDetailsService;

    @Test
    public void whenGetAllFileDetails_ThenReturnFileDetailsList() throws Exception {
        List<FileDetails> fileDetailsList = new ArrayList<>();
        fileDetailsList.add(new FileDetails(1L, "test.csv", FileStatus.PENDING, 0, 0));
        fileDetailsList.add(new FileDetails(2L, "test1.csv", FileStatus.PENDING, 0, 0));

        when(fileDetailsService.getAllFileDetails()).thenReturn(fileDetailsList);

        mockMvc.perform(get("/fileDetails/getAllFileDetails")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[1].filePath").value("test1.csv"));

    }

    @Test
    public void whenGetFileDetailsById_ThenReturnFileDetails() throws Exception {
        FileDetailsResponseDto fileDetailsResponseDto = new FileDetailsResponseDto();
        fileDetailsResponseDto.setFilePath("test.csv");
        fileDetailsResponseDto.setFileStatus(FileStatus.PENDING);

        when(fileDetailsService.getFileDetailsById(1L)).thenReturn(fileDetailsResponseDto);

        mockMvc.perform(get("/fileDetails/getFileDetailsById/1"))
                .andDo(print())
                .andExpect(jsonPath("$.filePath").value("test.csv"));

    }

    @Test
    public void whenAddNewFileDetails_ThenSaveFileDetails() throws Exception {
        FileDetailsRequestDto fileDetailsRequestDto = new FileDetailsRequestDto();
        fileDetailsRequestDto.setFileDetailsId(1L);
        fileDetailsRequestDto.setFilePath("test.csv");

        mockMvc.perform(post("/fileDetails/addNewFileDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fileDetailsRequestDto))
                )
                .andDo(print())
                .andExpect(status().isOk());

    }






}