package com.example.multipledatabaseconnection.fileDetails.model;

import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.timeStamps.TimeStamp;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class FileDetails extends TimeStamp {
    @Id
    @SequenceGenerator(sequenceName = "file_details_sequence",
            name = "file_details",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "file_details")

    private Long fileDetailsId;
    private String filePath;
    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;
    private Integer successCount;
    private Integer failureCount;

}
