package com.example.multipledatabaseconnection.fileDetails.repo;

import com.example.multipledatabaseconnection.fileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.fileDetails.model.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDetailsRepo extends JpaRepository<FileDetails,Long> {
    List<FileDetails> findByFileStatus(FileStatus fileStatus);
}
