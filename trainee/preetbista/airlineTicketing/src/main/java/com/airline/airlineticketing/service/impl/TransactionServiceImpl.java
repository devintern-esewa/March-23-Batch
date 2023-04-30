package com.airline.airlineticketing.service.impl;

import com.airline.airlineticketing.dto.TransactionDto;
import com.airline.airlineticketing.model.Transaction;
import com.airline.airlineticketing.repository.TransactionRepository;
import com.airline.airlineticketing.service.TransactionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public List<TransactionDto> getAllTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDtos.add(new TransactionDto(
                    transaction.getUser(),
                    transaction.getTicket(),
                    transaction.getTicketStatus()));
        }
        return transactionDtos;
    }

    @Override
    @Transactional
    public TransactionDto getTransactionByUserId(Long userId) {
        Optional<Transaction> optionalTransaction = transactionRepository.getTransactionByUserId(userId);
        if(optionalTransaction.isPresent()){
            Transaction transaction = optionalTransaction.get();
            return new TransactionDto(transaction.getUser(),
            transaction.getTicket(),
                    transaction.getTicketStatus());
        }else {
            return null;
        }
    }
}
