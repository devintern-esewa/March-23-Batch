package com.example.multipledbconnection.fileDetails.model;

import com.example.multipledbconnection.fileDetails.enums.FDStatus;
import lombok.*;

import javax.persistence.*;


@Entity
@Data
@Table (name="fileDetails")
@NoArgsConstructor
@AllArgsConstructor

public class FileDetails {
    @Id
    @SequenceGenerator(sequenceName = "files_detail_sequence",
            name = "file_details", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "file_details")
    private Long fileId;

    private String filePath;
    private Integer successCount;
    private Integer FailCount;

    @Enumerated(EnumType.STRING)
    private FDStatus fdStatus;

}
