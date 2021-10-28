package com.example.exercises.banking.transactions;

import java.time.LocalDateTime;

import com.example.exercises.currency.CurrencyAmount;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class TransactionTest {
    
    private static class TransactionImpl extends Transaction {
        
        public TransactionImpl(CurrencyAmount amount, LocalDateTime date) {
            super(amount, date);
        }
        
    }

}
