package com.example.mulltipledbconnectiontask.fileDetails.repo;

import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDetailsRepo extends JpaRepository<FileDetails, Long> {
    List<FileDetails> findByFileStatus(FileStatus fileStatus);
    FileDetails findByFilePath(String filePath);
}
