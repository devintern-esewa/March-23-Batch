package com.flight.flightcrud.utils;

import com.flight.flightcrud.exception.InsufficientAmountException;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {
    private static final Map<String, Double> paymentMap = new HashMap<>();

    static {
        paymentMap.put("acc1", 12000.0);
        paymentMap.put("acc2", 10000.0);
        paymentMap.put("acc3", 5000.0);
        paymentMap.put("acc4", 8000.0);
    }

    public static void validateCreditLimit(String accNo, double paidAmount) {
        if (paidAmount > paymentMap.get(accNo)) {
            throw new InsufficientAmountException("Amount exceeds");
        } else if (paidAmount < paymentMap.get(accNo)) {
            throw new InsufficientAmountException("Amount is insufficient");
        }
    }
}
