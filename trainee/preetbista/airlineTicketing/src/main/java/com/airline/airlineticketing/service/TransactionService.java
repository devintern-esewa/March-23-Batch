package com.airline.airlineticketing.service;

import com.airline.airlineticketing.dto.TransactionDto;
import com.airline.airlineticketing.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAllTransaction();
    TransactionDto getTransactionByUserId(Long userId);
}
