package com.configDb.dto;

import com.configDb.enums.FileStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponseDto {

    private String filePath;
    private FileStatusEnum status;
}
