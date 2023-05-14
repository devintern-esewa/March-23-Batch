package com.bismdhr.bankmgmtsystem.service;

import com.bismdhr.bankmgmtsystem.model.Loan;
import com.bismdhr.bankmgmtsystem.model.Role;
import com.bismdhr.bankmgmtsystem.model.User;

import java.util.List;

public interface LoanService {
    List<Loan> getAllLoans();
    Loan findById(int id);
    Loan addLoan(Loan role);
    Loan updateLoan(Loan loan);
    String deleteLoan(int id);
}
