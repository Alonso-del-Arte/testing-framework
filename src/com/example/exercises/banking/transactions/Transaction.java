package com.example.exercises.banking.transactions;

import java.time.LocalDateTime;

import com.example.exercises.currency.CurrencyAmount;

public abstract class Transaction {
    
    final CurrencyAmount trxAmount;
    
    final LocalDateTime trxDateTime;
    
    public Transaction(CurrencyAmount amount, LocalDateTime date) {
        this.trxAmount = amount;
        this.trxDateTime = date;
    }

}
