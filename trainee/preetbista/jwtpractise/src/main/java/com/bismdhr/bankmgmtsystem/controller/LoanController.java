package com.bismdhr.bankmgmtsystem.controller;

import com.bismdhr.bankmgmtsystem.model.Loan;
import com.bismdhr.bankmgmtsystem.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAll(){
        return loanService.getAllLoans();
    }
    @GetMapping("/{id}")
    public Loan findById(@PathVariable int id){
        return loanService.findById(id);
    }

    @PostMapping
    public Loan addLoan(@RequestBody Loan loan){
        return loanService.addLoan(loan);
    }

    @PutMapping
    public Loan updateLoan(@RequestBody Loan loan){
        return loanService.updateLoan(loan);
    }

    @DeleteMapping("/{id}")
    public String deleteLoan(@PathVariable int id){
        return loanService.deleteLoan(id);
    }
}
