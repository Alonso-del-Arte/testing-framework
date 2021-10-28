package com.example.exercises.currency;

public class CurrencyConversionNeededException extends RuntimeException {

    private static final long serialVersionUID = 4552264012015861760L;
    
    private final CurrencyAmount amountA, amountB;
    
    public CurrencyAmount convertAmountAToCurrencyB() {
        return CurrencyConverter.convert(this.amountA, this.amountB.currencyID);
    }
    
    public CurrencyAmount convertAmountBToCurrencyA() {
        return CurrencyConverter.convert(this.amountB, this.amountA.currencyID);
    }
    
    public CurrencyConversionNeededException(CurrencyAmount amtA, 
            CurrencyAmount amtB) {
        this.amountA = amtA;
        this.amountB = amtB;
    }

}
