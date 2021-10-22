package com.example.exercises.banking.transactions;

import java.time.LocalDateTime;

import com.example.exercises.currency.CurrencyAmount;

import testframegeneric.api.Test;
import static testframegeneric.api.Asserters.*;

public class TransactionTest {
	
	private static class TransactionImpl extends Transaction {
		
		public TransactionImpl(CurrencyAmount amount, LocalDateTime date) {
			super(amount, date);
		}
		
	}

}
