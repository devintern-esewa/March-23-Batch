package com.airline.airlineticketing.service;

import com.airline.airlineticketing.dto.TransactionDto;
import com.airline.airlineticketing.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<TransactionDto> getAllTransaction();
    Optional<TransactionDto> getTransactionByUserId(Long userId);
}
