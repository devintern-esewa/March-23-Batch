package com.example.multipledatabaseconnectiontask.configdb.repo;

import com.example.multipledatabaseconnectiontask.configdb.model.FileDetails;
import com.example.multipledatabaseconnectiontask.configdb.statusenum.FileStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDetailsRepository extends JpaRepository<FileDetails, Long> {
    List<FileDetails> findByStatus(FileStatusEnum fileStatusEnum);

    FileDetails getFileDetailsByFilepath(String filepath);
}
