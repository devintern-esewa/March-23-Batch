package com.example.multipledatabaseconnection.FileDetails.service;


import com.example.multipledatabaseconnection.FileDetails.dto.FileDetailsRequestDto;
import com.example.multipledatabaseconnection.FileDetails.dto.FileDetailsResponseDto;
import com.example.multipledatabaseconnection.FileDetails.enums.FileStatus;
import com.example.multipledatabaseconnection.FileDetails.model.FileDetails;
import com.example.multipledatabaseconnection.FileDetails.model.OtherInfo;
import com.example.multipledatabaseconnection.FileDetails.repo.FileDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileDetailsServiceImpl implements FileDetailsService{
    @Autowired
   private FileDetailsRepo fileDetailsRepo;

    @Override
    public List<FileDetails> getAllFileDetails() {
        return fileDetailsRepo.findAll();
    }

    @Override
    public FileDetailsResponseDto getFileDetailsById(Long fileDetailsId) {
        FileDetailsResponseDto fileDetailsResponseDto=new FileDetailsResponseDto();
        FileDetails fileDetails=fileDetailsRepo.findById(fileDetailsId).orElseThrow();
        fileDetailsResponseDto.setFilePath(fileDetails.getFilePath());
       fileDetailsResponseDto.setFileStatus(fileDetails.getFileStatus());
        return fileDetailsResponseDto;
    }

    @Override
    public FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        FileDetails fileDetails= new FileDetails();
        fileDetails.setFilePath(fileDetailsRequestDto.getFilePath());
        fileDetails.setFileStatus(FileStatus.PENDING);
        fileDetails.setFailureCount(0L);
        fileDetails.setSuccessCount(0L);

        return fileDetailsRepo.save(fileDetails);
    }

    @Override
    public void updateFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        Optional<FileDetails> fileDetails=fileDetailsRepo.findById(fileDetailsRequestDto.getFileDetailsId());
        FileDetails fileDetails1=fileDetails.get();
        fileDetails1.setFilePath(fileDetailsRequestDto.getFilePath());
        fileDetailsRepo.save(fileDetails1);
    }

    @Scheduled(cron = "0 */1 * * * * ") //to schedule the execution of method after 1 min of server start
    @Override
    public void readFile() throws IOException {
            List<FileDetails> fileDetails=fileDetailsRepo.findByFileStatus(FileStatus.PENDING);
                for(FileDetails fileDetail:fileDetails){
                    fileDetail.setFileStatus(FileStatus.PROCESSING);
                    fileDetailsRepo.save(fileDetail);
                    String csvFile=fileDetail.getFilePath();
                    String csvSeparator=",";
                    BufferedReader reader=new BufferedReader(new FileReader(csvFile));
                    String line=" ";
                    int value=0;
                    while((line= reader.readLine())!=null){
                        if(value>=1){
                            String[] row=line.split(csvSeparator);
                            String id=row[0];
                            String name=row[1];
                            String status=row[2];
                            String code=row[3];
                            int quantity=Integer.valueOf(row[4]);
                            double price=Double.parseDouble(row[5]);
                            System.out.println(id + " "+ name+ " "+ status+ " "+ quantity+ " "+price);
                        }
                        value++;
                    }

                    reader.close();

                    //created new transaction to update FileStatus
                    FileDetails fileDetails1=fileDetailsRepo.findById(fileDetail.getFileDetailsId()).orElseThrow();
                    fileDetails1.setFileStatus(FileStatus.COMPLETE);
                    fileDetailsRepo.save(fileDetails1);
                }
            }


}
