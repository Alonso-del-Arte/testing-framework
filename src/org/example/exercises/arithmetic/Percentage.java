package org.example.exercises.arithmetic;

public class Percentage {
    
    // TODO: Enable percentages like 53.9%, 71.2908%
    private final int number;
    
    @Override
    public String toString() {
        return this.number + ".0%";
    }
    
    public Percentage(int n) {
        this.number = n;
    }

}
