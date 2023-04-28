package com.example.mulltipledbconnectiontask.fileDetails.model;

import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class FileDetails extends OtherInfo{
    @Id
    @SequenceGenerator(sequenceName = "file_details_sequence",
            name="file_details",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "file_details")
    private Long productFileId;
    private String filePath;
    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;
    private Long successCount;
    private Long failureCount;
}

