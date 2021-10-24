package com.example.exercises.currency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

import static testframe.api.Asserters.*;
import testframe.api.Test;

public class CurrencyAmountTest {
	
	private static final Random RANDOM = new Random();
	
    private static final Currency DINARS 
            = Currency.getInstance(Locale.forLanguageTag("ar-LY"));
	private static final Currency U_S_DOLLARS = Currency.getInstance(Locale.US);
	private static final Currency EUROS = Currency.getInstance("EUR");
    private static final Currency FRANCS 
            = Currency.getInstance(Locale.forLanguageTag("fr-CH"));
    private static final Currency YEN = Currency.getInstance(Locale.JAPAN);

    @Test
    public void testToString() {
    	System.out.println("toString");
    	int dollars = RANDOM.nextInt(1000) + 1;
    	int cents = RANDOM.nextInt(90) + 10;
    	CurrencyAmount amount = new CurrencyAmount(100 * dollars + cents, 
    			U_S_DOLLARS);
    	String expected = "$" + dollars + "." + cents;
    	String actual = amount.toString();
    	assertEquals(expected, actual);
    }

}
