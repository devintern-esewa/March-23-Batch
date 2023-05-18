package com.example.multipledbconnection.fileDetails.service;

import com.example.multipledbconnection.fileDetails.dto.DtoFDRequest;
import com.example.multipledbconnection.fileDetails.dto.DtoFDResponse;
import com.example.multipledbconnection.fileDetails.enums.FDStatus;
import com.example.multipledbconnection.fileDetails.exception.IdNotFoundException;
import com.example.multipledbconnection.fileDetails.model.FileDetails;
import com.example.multipledbconnection.fileDetails.repo.FDRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FDServiceImpl implements FDService {

    public final FDRepo fdRepo;


    public FDServiceImpl(FDRepo fdRepo) {
        this.fdRepo = fdRepo;
    }


    @Override
    public List<FileDetails> getAllFD() {
        return fdRepo.findAll();
    }

    @Override
    public FileDetails addFD(DtoFDRequest dtoFDRequest) {
        System.out.println(dtoFDRequest);
        FileDetails fd = new FileDetails();
        fd.setFilePath(dtoFDRequest.getFilePath());
        fd.setFdStatus(FDStatus.PENDING);
        fd.setFailCount(0);
        fd.setSuccessCount(0);
        return fdRepo.save(fd);
    }

    @Override
    public DtoFDResponse getFDByID(Long fileId) {
        DtoFDResponse dtoR = new DtoFDResponse();
        FileDetails fileDetails = fdRepo.findById(fileId).orElseThrow
                (
                        () -> new IdNotFoundException(fileId + " doesn't exist")
                );
        dtoR.setFilePath(fileDetails.getFilePath());
        dtoR.setFdStatus(fileDetails.getFdStatus());
        return dtoR;
    }

    @Override
    public FileDetails updateFD(DtoFDRequest dtoFDRequest, Long fileId) {

        FileDetails dto = new FileDetails();
        dto = fdRepo.findById(fileId).get();
        dto.setFilePath(dtoFDRequest.getFilePath());
        return fdRepo.save(dto);
    }


    @Scheduled (cron = "0 */1 * * * * ")
    public void readFile() throws IOException {

        fdRepo.findByFdStatus(FDStatus.PENDING).stream()
                .forEach(fileDetail -> {
                    fileDetail.setFdStatus(FDStatus.PROCESSING);
                    fdRepo.save(fileDetail);
                });

//        List<FileDetails> fileDetails = fdRepo.findByFdStatus(FDStatus.PENDING);
//        for (FileDetails fileDetail : fileDetails) {
//            fileDetail.setFdStatus(FDStatus.PROCESSING);
//            fdRepo.save(fileDetail);
//       }

    }
}

