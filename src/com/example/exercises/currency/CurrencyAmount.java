package com.example.exercises.currency;

import java.util.Currency;

public class CurrencyAmount {
	
	private final long centsAmount;
	
	private final Currency currencyID;
	
	// TODO: Write tests for this, e.g., JPY, LYD
	@Override
	public String toString() {
		double amount = (double) this.centsAmount / 100.0;
		return this.currencyID.getSymbol() + amount;
	}
	
	public CurrencyAmount(long cents, Currency currency) {
		this.centsAmount = cents;
		this.currencyID = currency;
	}

}
