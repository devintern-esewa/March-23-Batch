package com.configDb.repository;

import com.configDb.enums.FileStatusEnum;
import com.configDb.model.FileDetail;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FileDetailRepositoryTest {

    @Autowired
    private FileDetailRepository fileDetailRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Order(1)
    public void saveFileDetail(){
        FileDetail detail = FileDetail.builder()
                .filePath("D:/don.csv")
                .fileStatus(FileStatusEnum.PENDING)
                .failureCount(0)
                .successCount(1)
                .build();

        fileDetailRepository.save(detail);

        assertThat(detail).hasFieldOrPropertyWithValue("D:/don.csv", 1);

    }

    @Test
    @Order(2)
    public void getFileDetail(){
        fileDetailRepository.findAll().forEach(fileDetail -> System.out.println(fileDetail.getFilePath()));
    }

    @Test
    public void should_find_all_files(){
        FileDetail detail = FileDetail.builder()
                .filePath("D:/don.csv")
                .fileStatus(FileStatusEnum.PENDING)

                .failureCount(0)
                .successCount(1)

                .build();
        entityManager.persist(detail);

        FileDetail detail2 = FileDetail.builder()
                .filePath("D:/don.csv")
                .fileStatus(FileStatusEnum.PENDING)
                .failureCount(0)
                .successCount(1)
                .build();
        entityManager.persist(detail2);

        List<FileDetail> fileDetail = fileDetailRepository.findAll();
        assertThat(fileDetail).hasSize(2).contains(detail,detail2);
    }

    @Test
    public void getFileDetail_whenNoFileDetailFound_returnEmpty(){
        List<FileDetail> fileDetail = fileDetailRepository.findAll();
        assertThat(fileDetail).isEmpty();
    }

    @Test
    public void findByStatus(){
        FileDetail detail = FileDetail.builder()
                .fileStatus(FileStatusEnum.PENDING)
                .build();
        entityManager.persist(detail);

        List<FileDetail> file = fileDetailRepository.findByFileStatus(FileStatusEnum.PROCESSING);
        assertThat(file).hasSize(1).contains(detail);
    }
}