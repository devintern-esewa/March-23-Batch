package com.example.multipledatabaseconnection.FileDetails.repo;

import com.example.multipledatabaseconnection.FileDetails.model.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDetailsRepo extends JpaRepository<FileDetails,Long> {
}
