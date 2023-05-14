package com.bismdhr.bankmgmtsystem.service.impl;

import com.bismdhr.bankmgmtsystem.dto.InsuranceUpdateDto;
import com.bismdhr.bankmgmtsystem.dto.UserUpdateDto;
import com.bismdhr.bankmgmtsystem.exception.NotFoundException;
import com.bismdhr.bankmgmtsystem.model.Insurance;
import com.bismdhr.bankmgmtsystem.model.Loan;
import com.bismdhr.bankmgmtsystem.model.User;
import com.bismdhr.bankmgmtsystem.repository.InsuranceRepository;
import com.bismdhr.bankmgmtsystem.repository.UserRepository;
import com.bismdhr.bankmgmtsystem.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    @Override
    public Insurance findById(int id) {
        Optional<Insurance> optionalUser = insuranceRepository.findById(id);
        return optionalUser.orElseThrow(() -> new NotFoundException("Insurance not found for id :"+id));
    }
    @Override
    public Insurance findByFullName(String fullName) {
        return null;
    }

    @Override
    public Insurance addInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    @Override
    public Insurance updateInsurance(InsuranceUpdateDto updateDto) {
        Insurance insurance = findById(updateDto.getId());
        insurance.setFullName(updateDto.getFullName());
        insurance.setContact(updateDto.getContact());
        insurance.setYears(updateDto.getYears());
        insurance.setType(updateDto.getType());
        insurance.setDob(updateDto.getDob());
        return insuranceRepository.save(insurance);
    }

    @Override
    public String deleteInsurance(int id) {
        findById(id);
        insuranceRepository.deleteById(id);
        return "Insurance deleted successfully";
    }
}
