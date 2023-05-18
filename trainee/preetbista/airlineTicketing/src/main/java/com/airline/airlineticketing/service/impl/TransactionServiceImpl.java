package com.airline.airlineticketing.service.impl;

import com.airline.airlineticketing.dto.TransactionDto;
import com.airline.airlineticketing.repository.TransactionRepository;
import com.airline.airlineticketing.service.TransactionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public List<TransactionDto> getAllTransaction() {
        return transactionRepository.findAll().stream()
                .map(TransactionDto::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<TransactionDto> getTransactionByUserId(Long userId) {
        return transactionRepository.getTransactionByUserId(userId)
                .map(transaction -> new TransactionDto(
                        transaction.getUser(),
                        transaction.getTicket(),
                        transaction.getTicketStatus()));
    }
}
