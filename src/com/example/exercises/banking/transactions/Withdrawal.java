package com.example.exercises.banking.transactions;

import java.time.LocalDateTime;

import com.example.exercises.currency.CurrencyAmount;

public class Withdrawal extends Transaction {
	
	public Withdrawal(CurrencyAmount amount, LocalDateTime date) {
		super(amount, date);
	}

}
