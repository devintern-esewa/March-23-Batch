package com.configDb.service;

import com.configDb.dto.FileRequestDto;
import com.configDb.dto.FileResponseDto;

public interface FileDetailService {
    FileResponseDto saveFileDetail(FileRequestDto fileRequestDto);
}
