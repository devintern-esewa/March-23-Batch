package com.example.multipledatabaseconnection.FileDetails.model;

import com.example.multipledatabaseconnection.FileDetails.enums.FileStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table
public class FileDetails extends OtherInfo {
    @Id
    @SequenceGenerator(sequenceName = "file_details_sequence",
    name="file_details",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "file_details")

    private Long fileDetailsId;

    private String filePath;
    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;


    private Long successCount;
    private Long failureCount;

}
