package org.example.demo.textops;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class PalindromeCheckerTest {
    
    @Test
    public void testIsPalindromic() {
        System.out.println("isPalindromic");
        PalindromeChecker checker = new PalindromeChecker();
        String word = "kayak";
        String msg = "\"" + word + "\" should be considered palindromic";
        assert checker.isPalindromic(word) : msg;
    }

}
