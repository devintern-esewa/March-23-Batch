package com.airline.airlineticketing.repository;

import com.airline.airlineticketing.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select t from transactions t where t.user_id = :userId", nativeQuery = true)
    Optional<Transaction> getTransactionByUserId(Long userId);
}
