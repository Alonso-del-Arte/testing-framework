package com.example.exercises.currency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

import static testframe.api.Asserters.*;
import testframe.api.Test;

public class CurrencyConverterTest {

	private static final Random RANDOM = new Random();
	
    private static final Currency DINARS 
            = Currency.getInstance(Locale.forLanguageTag("ar-LY"));
    
	private static final Currency U_S_DOLLARS = Currency.getInstance(Locale.US);
	
	/**
	 * East Caribbean dollars. The conversion rate US$1.00 = XCD2.70 will hold 
	 * for as long as the Eastern Caribbean Central Bank can hold it. 
	 */
	private static final Currency EAST_CARIBBEAN_DOLLARS 
	        = Currency.getInstance("XCD");
	
	private static final Currency EUROS = Currency.getInstance("EUR");
	
    private static final Currency FRANCS 
            = Currency.getInstance(Locale.forLanguageTag("fr-CH"));
    
    private static final Currency YEN = Currency.getInstance(Locale.JAPAN);

}
