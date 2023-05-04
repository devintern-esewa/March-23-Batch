package com.example.mulltipledbconnectiontask.fileDetails.controller;


import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsRequestDto;
import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsResponseDto;
import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import com.example.mulltipledbconnectiontask.fileDetails.service.FileDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileDetailsController.class)
class FileDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileDetailsService fileDetailsService;

    @Test
    public void getAllFileDetailsTest() throws Exception {
        List<FileDetails> fileDetailsList = new ArrayList<>();
        fileDetailsList.add(new FileDetails(1L, "test1.csv", FileStatus.PENDING, 0L, 0L));
        fileDetailsList.add(new FileDetails(2L, "test2.csv", FileStatus.PENDING, 0L, 0L));

        when(fileDetailsService.getALLFileDetails()).thenReturn(fileDetailsList);

        mockMvc.perform(get("/api/FileDetails/getAllFileDetails"))
                .andDo(print())
                .andExpect(jsonPath("$[1].filePath", is("test2.csv")));
    }

    @Test
    public void getFileDetailsByIdTest() throws Exception {
        FileDetailsResponseDto fileDetailsResponseDto = new FileDetailsResponseDto();
        fileDetailsResponseDto.setFilePath("test.csv");
        fileDetailsResponseDto.setFileStatus(FileStatus.PENDING);

        when(fileDetailsService.getFileDetailsById(anyLong())).thenReturn(fileDetailsResponseDto);

        mockMvc.perform(get("/api/FileDetails/getFileDetailsById/1"))
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.filePath", is("test.csv")));
    }

    @Test
    public void addNewFileDetailsTest() throws Exception {
        FileDetailsRequestDto fileDetailsRequestDto = new FileDetailsRequestDto();
        fileDetailsRequestDto.setFilePath("test.csv");

        mockMvc.perform(post("/api/FileDetails/addNewFileDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fileDetailsRequestDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}