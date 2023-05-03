package com.configDb.repository;

import com.configDb.enums.FileStatusEnum;
import com.configDb.model.FileDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FileDetailRepositoryTest {

    @Autowired
    private FileDetailRepository fileDetailRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByFileStatus() {

        FileDetail fileDetail1 = FileDetail.builder()
                .filePath("D:/don.csv")
                .fileStatus(FileStatusEnum.COMPLETE)
                .build();

        entityManager.persist(fileDetail1);

        FileDetail fileDetail2 = FileDetail.builder()
                .filePath("D:/don.csv")
                .fileStatus(FileStatusEnum.PENDING)
                .build();

        entityManager.persist(fileDetail2);
        entityManager.flush();

        List<FileDetail> results = fileDetailRepository.findByFileStatus(FileStatusEnum.COMPLETE);

        assertThat(results.size()).isEqualTo(1);
        assertThat(results.get(0).getFilePath()).isEqualTo(fileDetail1.getFilePath());
    }

    @Test
    public void testFindFileDetailByFilePath() {
        // create test data
        FileDetail fileDetail = FileDetail.builder()
                .filePath("file.txt")
                .fileStatus(FileStatusEnum.COMPLETE)
                .build();

        entityManager.persist(fileDetail);
        entityManager.flush();

        FileDetail result = fileDetailRepository.findFileDetailByFilePath("file.txt");

        assertThat(result.getFilePath()).isEqualTo(fileDetail.getFilePath());
        assertThat(result.getFileStatus()).isEqualTo(fileDetail.getFileStatus());
    }

}