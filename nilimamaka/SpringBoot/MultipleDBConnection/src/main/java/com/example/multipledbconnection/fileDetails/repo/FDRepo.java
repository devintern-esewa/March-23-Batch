package com.example.multipledbconnection.fileDetails.repo;

import com.example.multipledbconnection.fileDetails.enums.FDStatus;
import com.example.multipledbconnection.fileDetails.model.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FDRepo extends JpaRepository<FileDetails, Long> {
    List<FileDetails> findByFdStatus(FDStatus fdStatus);

    FileDetails findByFilePath(String filePath);
}
