package com.configDb.controller;

import com.configDb.dto.FileRequestDto;
import com.configDb.dto.FileResponseDto;
import com.configDb.enums.FileStatusEnum;
import com.configDb.service.FileDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileDetailController.class)
class FileDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileDetailService fileDetailService;

    @Test
    void testCreationOfFile() throws Exception {

        FileResponseDto fileResponseDto = FileResponseDto.builder()
                .filePath("D:/products.csv")
                .status(FileStatusEnum.PENDING)
                .build();

        when(fileDetailService.saveFileDetail(ArgumentMatchers.any(FileRequestDto.class))).thenReturn(fileResponseDto);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();

        String content = objectWriter.writeValueAsString(fileResponseDto);

        mockMvc.perform(
                        post("/fileDetail")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.filePath", is("D:/products.csv")));
    }

}