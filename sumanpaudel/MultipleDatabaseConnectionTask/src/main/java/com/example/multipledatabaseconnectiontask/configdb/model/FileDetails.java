package com.example.multipledatabaseconnectiontask.configdb.model;

import com.example.multipledatabaseconnectiontask.configdb.statusenum.FileStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDetails {
    @Id
    @SequenceGenerator(name = "file", sequenceName = "file", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file")
    private long id;
    private String filepath;
    @Enumerated(EnumType.STRING)
    private FileStatusEnum status;
    private int success_count;
    private int failure_count;
    @CreationTimestamp
    private LocalDateTime createdTime;
}
