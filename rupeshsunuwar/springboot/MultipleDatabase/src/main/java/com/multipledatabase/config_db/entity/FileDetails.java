package com.multipledatabase.config_db.entity;


import com.multipledatabase.config_db.enums.FileDetailsEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_details")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String file_Path;
    @Enumerated(EnumType.STRING)
    private FileDetailsEnum status;
    private int success_Count;
    private int failure_Count;
    @CreationTimestamp
    private LocalDateTime created;




}
