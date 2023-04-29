package com.example.multipledatabaseconnection.FileDetails.repo;

import com.example.multipledatabaseconnection.FileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.FileDetails.model.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDetailsRepo extends JpaRepository<FileDetails,Long> {
    List<FileDetails> findByFileStatus(FileStatus fileStatus);
}
