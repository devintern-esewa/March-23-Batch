package com.multipledatabase.configdb.repository;

import com.multipledatabase.configdb.entity.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails, Integer> {
}
