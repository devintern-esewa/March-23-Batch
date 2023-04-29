package com.example.mulltipledbconnectiontask.fileDetails.service;

import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsRequestDto;
import com.example.mulltipledbconnectiontask.fileDetails.dto.FileDetailsResponseDto;
import com.example.mulltipledbconnectiontask.fileDetails.enums.FileStatus;
import com.example.mulltipledbconnectiontask.fileDetails.model.FileDetails;
import com.example.mulltipledbconnectiontask.fileDetails.repo.FileDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class FileDetailsServiceImpl implements FileDetailsService{
    @Autowired
    private FileDetailsRepo fileDetailsRepo;

    @Override
    public List<FileDetails> getALLFileDetails() {
        return fileDetailsRepo.findAll();
    }

    @Override
    public FileDetails addNewFileDetails(FileDetailsRequestDto fileDetailsRequestDto) {
        FileDetails fileDetails=new FileDetails();
        fileDetails.setFilePath(fileDetailsRequestDto.getFilePath());
        fileDetails.setFileStatus(FileStatus.PENDING);
        fileDetails.setSuccessCount(0L);
        fileDetails.setFailureCount(0L);
        return fileDetailsRepo.save(fileDetails);
    }

    @Override
    public FileDetailsResponseDto getFileDetailsById(Long fileDetailsId) {
        FileDetailsResponseDto fileDetailsResponseDto=new FileDetailsResponseDto();
        FileDetails fileDetails=fileDetailsRepo.findById(fileDetailsId).orElseThrow();
        fileDetailsResponseDto.setFilePath(fileDetails.getFilePath());
        fileDetailsResponseDto.setFileStatus(fileDetails.getFileStatus());
        return fileDetailsResponseDto;
    }

    @Scheduled(cron = "0 */1 * * * *") // execute after 1 min of server start
    public void readFile() throws IOException {
        List<FileDetails> fileDetails=fileDetailsRepo.findByFileStatus(FileStatus.PENDING);
        for(FileDetails file:fileDetails){
            file.setFileStatus(FileStatus.PROCESSING);
            fileDetailsRepo.save(file);

            String csvFile=file.getFilePath();
            String csvSeparator = ",";
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String line = " ";
            int value = 0;
            while ((line = reader.readLine()) != null) {
                if(value>=1){
                    String[] row = line.split(csvSeparator);
                    String id=row[0];
                    String name=row[1];
                    String status=row[2];
                    String code=row[3];
                    int quantity= Integer.parseInt(row[4]);
                    double price= Double.parseDouble(row[5]);
                    System.out.println(id + " " + name + " " + status + " " + code + " " + quantity + " " + price);
                }
                value++;
            }
            reader.close();

            // created a new file to set status to complete
            FileDetails updatedFile = fileDetailsRepo.findById(file.getFileDetailsId()).orElseThrow();
            updatedFile.setFileStatus(FileStatus.COMPLETE);
            fileDetailsRepo.save(updatedFile);
        }
    }
}
