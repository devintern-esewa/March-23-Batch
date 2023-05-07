package com.multipledatabase.configdb.repository;

import com.multipledatabase.configdb.entity.FileDetails;
import com.multipledatabase.configdb.enums.FileDetailsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails, Integer> {


    public List<FileDetails> findByStatus(FileDetailsEnum fileDetailsEnum);
}
