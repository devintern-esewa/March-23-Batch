package com.bismdhr.bankmgmtsystem.service.impl;

import com.bismdhr.bankmgmtsystem.exception.NotFoundException;
import com.bismdhr.bankmgmtsystem.model.Loan;
import com.bismdhr.bankmgmtsystem.model.Role;
import com.bismdhr.bankmgmtsystem.repository.LoanRepository;
import com.bismdhr.bankmgmtsystem.repository.RoleRepository;
import com.bismdhr.bankmgmtsystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan findById(int id) {
        Optional<Loan> optionalRole = loanRepository.findById(id);
        return optionalRole.orElseThrow(() -> new NotFoundException("Loan not found for id :"+id));
    }

    @Override
    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan updateLoan(Loan loan) {
        findById(loan.getId());
        return loanRepository.save(loan);
    }

    @Override
    public String deleteLoan(int id) {
        findById(id);
        loanRepository.deleteById(id);
        return "Loan deleted successfully";
    }
}
