package com.configDb.model;

import com.configDb.enums.FileStatusEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "file_details")
public class FileDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "don")
    @SequenceGenerator(
            name = "don",
            sequenceName = "file_detail_sequence",
            allocationSize = 1
    )
    private long id;

    private String filePath;

    @Enumerated(EnumType.STRING)
    private FileStatusEnum fileStatus;

    private int successCount;
    private int failureCount;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastModifiedDate;

}
