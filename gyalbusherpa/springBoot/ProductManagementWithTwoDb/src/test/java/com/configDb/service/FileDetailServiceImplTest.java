package com.configDb.service;

import com.configDb.dto.FileRequestDto;
import com.configDb.dto.FileResponseDto;
import com.configDb.enums.FileStatusEnum;
import com.configDb.model.FileDetail;
import com.configDb.repository.FileDetailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileDetailServiceImplTest {

    @Mock
    private FileDetailRepository fileDetailRepository;

    @InjectMocks
    private FileDetailServiceImpl fileDetailService;

    @Test
    public void saveFileDetail_shouldSaveFileDetailAndReturnResponseDto() {
        FileRequestDto fileRequestDto = new FileRequestDto();
        fileRequestDto.setFilePath("path/to/file");

        FileDetail savedFileDetail = new FileDetail();
        savedFileDetail.setId(1L);
        savedFileDetail.setFileStatus(FileStatusEnum.PENDING);
        savedFileDetail.setFilePath(fileRequestDto.getFilePath());

        when(fileDetailRepository.save(any(FileDetail.class))).thenReturn(savedFileDetail);

        FileResponseDto fileResponseDto = fileDetailService.saveFileDetail(fileRequestDto);

        assertNotNull(fileResponseDto);
        assertEquals(fileRequestDto.getFilePath(), fileResponseDto.getFilePath());
        assertEquals(FileStatusEnum.PENDING, fileResponseDto.getStatus());
        verify(fileDetailRepository, times(1)).save(any(FileDetail.class));
    }

}