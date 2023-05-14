package com.bismdhr.bankmgmtsystem.service;

import com.bismdhr.bankmgmtsystem.dto.InsuranceUpdateDto;
import com.bismdhr.bankmgmtsystem.model.Insurance;

import java.util.List;

public interface InsuranceService {
    List<Insurance> getAllInsurances();

    Insurance findById(int id);

    Insurance findByFullName(String fullName);

    Insurance addInsurance(Insurance insurance);

    Insurance updateInsurance(InsuranceUpdateDto insurance);

    String deleteInsurance(int id);
}
