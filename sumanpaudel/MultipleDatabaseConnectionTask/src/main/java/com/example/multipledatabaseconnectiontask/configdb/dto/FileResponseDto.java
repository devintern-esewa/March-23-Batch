package com.example.multipledatabaseconnectiontask.configdb.dto;

import com.example.multipledatabaseconnectiontask.configdb.statusenum.FileStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileResponseDto {
    private String file_path;
    private FileStatusEnum status;
}
