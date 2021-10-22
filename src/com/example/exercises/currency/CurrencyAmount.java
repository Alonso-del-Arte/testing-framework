package com.example.exercises.currency;

import java.util.Currency;

import org.example.exercises.arithmetic.Percentage;

public class CurrencyAmount implements Comparable<CurrencyAmount> {
	
	final long centsAmount;
	
	final Currency currencyID;
	
	// TODO: Write tests for this, e.g., JPY, LYD
	@Override
	public String toString() {
		double amount = (double) this.centsAmount / 100.0;
		return this.currencyID.getSymbol() + amount;
	}
	
	// TODO: Write tests for this
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	// TODO: Write tests for this
	@Override
	public int hashCode() {
		return 0;
	}
	
	// TODO: Write tests for this
	public CurrencyAmount plus(CurrencyAmount addend) {
		return this;
	}
	
	public CurrencyAmount negate() {
		return this.times(-1);
	}
	
	public CurrencyAmount minus(CurrencyAmount subtrahend) {
		return this.plus(subtrahend.negate());
	}
	
	// TODO: Write tests for this
	public CurrencyAmount times(int multiplicand) {
		return this;
	}
	
	// TODO: Write tests for this
	public CurrencyAmount times(Percentage multiplicand) {
		return this;
	}
	
	// TODO: Write tests for this, e.g., division by 0
	public CurrencyAmount divides(int divisor) {
		return this;
	}
	
	// TODO: Write tests for this
	public int compareTo(CurrencyAmount other) {
		return 0;
	}
	
	public CurrencyAmount(long cents, Currency currency) {
		this.centsAmount = cents;
		this.currencyID = currency;
	}

}
