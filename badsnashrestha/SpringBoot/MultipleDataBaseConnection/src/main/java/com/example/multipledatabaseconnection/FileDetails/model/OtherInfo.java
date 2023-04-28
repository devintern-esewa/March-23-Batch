package com.example.multipledatabaseconnection.FileDetails.model;

import com.example.multipledatabaseconnection.FileDetails.enums.FileStatus;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@MappedSuperclass

public class OtherInfo {
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    @PrePersist
    public void createdDate(){
        this.createdDate=LocalDateTime.now();
        modifiedDate=LocalDateTime.now();
    }


    @PreUpdate
    public void preUpdate(){
            modifiedDate= LocalDateTime.now();
    }
    @Version
    Integer version;
}
