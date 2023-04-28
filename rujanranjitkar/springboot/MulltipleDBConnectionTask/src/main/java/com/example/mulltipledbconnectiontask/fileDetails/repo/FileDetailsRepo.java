package com.example.mulltipledbconnectiontask.fileDetails.repo;

import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDetailsRepo extends JpaRepository<FileDetails, Long> {
}
