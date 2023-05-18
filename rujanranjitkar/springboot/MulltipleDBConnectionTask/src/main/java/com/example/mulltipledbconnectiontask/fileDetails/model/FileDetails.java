package com.example.mulltipledbconnectiontask.fileDetails.model;

import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import com.example.mulltipledbconnectiontask.timeStamp.TimeStamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Long successCount;
    private Long failureCount;
}

