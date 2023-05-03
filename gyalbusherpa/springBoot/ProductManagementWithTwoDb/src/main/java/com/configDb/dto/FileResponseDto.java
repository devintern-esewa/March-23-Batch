package com.configDb.dto;

import com.configDb.enums.FileStatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDto {

    private String filePath;
    private FileStatusEnum status;
}
