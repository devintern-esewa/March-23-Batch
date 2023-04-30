package com.example.mulltipledbconnectiontask.timeStamp;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class TimeStamp {

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @PrePersist
    private void prePersist() {
        createdDate = LocalDateTime.now();
        lastModifiedDate = createdDate;
    }

    @PreUpdate
    private void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }

    @Version
    private Long version;
}
