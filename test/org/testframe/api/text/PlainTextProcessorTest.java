package org.testframe.api.text;

import static org.testframe.api.Asserters.*;

import java.util.Random;

import org.testframe.api.Test;
import org.testframe.api.text.PlainTextProcessor;

public class PlainTextProcessorTest {
    
    static final Random RANDOM = new Random();
    
    private static final char[] WHITESPACE_CHARS = {' ', '\t', '\n', '\u000B', 
            '\f', '\r', '\u001C', '\u001D', '\u001E', '\u001F'};
    
    private static final int NUMBER_OF_WHITESPACE_CHARS 
            = WHITESPACE_CHARS.length;
    
    @Test
    public void testLowestUnassignedUnicodeCharacter() {
        char expected = '\u0000';
        while (Character.isDefined(expected)) {
            expected++;
        }
        char actual = PlainTextProcessor.lowestUnassignedUnicodeCharacter();
        char highestAssigned = (char) (expected - 1);
        String msg = "Character after '" + highestAssigned + "' (" 
                + Character.getName(highestAssigned) + ") should be unassigned";
        assertEquals(expected, actual, msg);
    }
    
    private static String randomSpacesAndTabs() {
        int len = RANDOM.nextInt(8) + 2;
        char[] array = new char[len];
        for (int i = 0; i < len; i++) {
            if (RANDOM.nextBoolean()) {
                array[i] = ' ';
            } else {
                array[i] = '\t';
            }
        }
        return new String(array);
    }
    
    private static String randomWhitespace() {
        int len = RANDOM.nextInt(4) + 1;
        char[] array = new char[len];
        for (int i = 0; i < len; i++) {
            array[i] = WHITESPACE_CHARS[RANDOM
                                        .nextInt(NUMBER_OF_WHITESPACE_CHARS)];
        }
        return new String(array);
    }
    
    @Test
    public void testEqualsWithWhitespaceCollapsed() {
        System.out.println("equalsWithWhitespaceCollapsed");
        String numStrA = Integer.toString(RANDOM.nextInt());
        String numStrB = Integer.toString(RANDOM.nextInt());
        String s1 = numStrA + ' ' + numStrB;
        String s2 = numStrA + " \t" + numStrB;
        String msg = "\"" + s1 + "\" should equal \"" + s2 
                + "\" with whitespace collapsed";
        assert PlainTextProcessor.equalsWithWhitespaceCollapsed(s1, s2) : msg;
    }
    
    @Test
    public void testCollapseWhitespaceSimplyReturnsEmptyStringParam() {
        String expected = "";
        String actual = PlainTextProcessor.collapseWhitespace(expected);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCollapseSpacesAndTabs() {
        StringBuilder toBeExpected = new StringBuilder();
        StringBuilder toBeProcessed = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            toBeExpected.append(c);
            toBeExpected.append(' ');
            toBeProcessed.append(c);
            toBeProcessed.append(randomSpacesAndTabs());
        }
        String s = toBeProcessed.toString();
        String expected = toBeExpected.toString();
        String actual = PlainTextProcessor.collapseWhitespace(s);
        String msg = "Attempting to collapse whitespace in \"" + s + "\"";
        assertEquals(expected, actual, msg);
    }

    @Test
    public void testCollapseWhitespace() {
        System.out.println("collapseWhitespace");
        StringBuilder toBeExpected = new StringBuilder();
        StringBuilder toBeProcessed = new StringBuilder();
        for (char c = '0'; c <= '9'; c++) {
            toBeExpected.append(c);
            toBeExpected.append(' ');
            toBeProcessed.append(c);
            toBeProcessed.append(randomWhitespace());
        }
        String s = toBeProcessed.toString();
        String expected = toBeExpected.toString();
        String actual = PlainTextProcessor.collapseWhitespace(s);
        String msg = "Attempting to collapse whitespace in \"" + s + "\"";
        assertEquals(expected, actual, msg);
    }

    public void testDoesNotEqualWithWhitespaceCollapsed() {
        String numStrA = Integer.toString(RANDOM.nextInt());
        String numStrB = Integer.toString(RANDOM.nextInt());
        String s1 = numStrA + ' ' + numStrB;
        String s2 = numStrA + '_' + numStrB;
        String msg = "\"" + s1 + "\" should not equal \"" + s2 
                + "\" with whitespace collapsed";
        assert !PlainTextProcessor.equalsWithWhitespaceCollapsed(s1, s2) : msg;
    }
    
}
