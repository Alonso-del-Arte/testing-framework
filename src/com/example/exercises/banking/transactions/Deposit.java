package com.example.exercises.banking.transactions;

import java.time.LocalDateTime;

import com.example.exercises.currency.CurrencyAmount;

public class Deposit extends Transaction {
    
    public Deposit(CurrencyAmount amount, LocalDateTime date) {
        super(amount, date);
    }

}
