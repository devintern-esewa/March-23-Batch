package com.configDb.repository;

import com.configDb.enums.FileStatusEnum;
import com.configDb.model.FileDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDetailRepository extends JpaRepository<FileDetail, Long> {
    List<FileDetail> findByFileStatus(FileStatusEnum status);
}
