package org.example.demo.textops;

import java.util.Arrays;

public class PalindromeChecker {
    
    private boolean caseSensitive = true;
    
    // TODO: Write tests for this
    public boolean isCaseSensitive() {
        return false;
    }
    
    // TODO: Write tests for this
    public void setCaseSensitivity(boolean sensitivity) {
//this.caseSensitive = sensitivity;
    }
    
    // TODO: Write tests for this
    public boolean isPalindromic(String term) {
        return false;
    }
    
    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();
        if (args.length > 0) {
            for (String arg : args) {
                switch (arg) {
                    case "-case":
                        checker.setCaseSensitivity(true);
                        break;
                    case "-noCase":
                        checker.setCaseSensitivity(false);
                        break;
                    case "-version":
                        System.out.println("Palindrome Checker Version 0.0");
                        break;
                    default:
                        System.out.print("\"" + arg + "\" is ");
                        if (!checker.isPalindromic(arg)) {
                            System.out.print("NOT ");
                        }
                        System.out.println("palindromic");
                }
            }
        }
    }

}
