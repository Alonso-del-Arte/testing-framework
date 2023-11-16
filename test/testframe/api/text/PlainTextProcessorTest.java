package testframe.api.text;

import java.util.Random;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class PlainTextProcessorTest {
    
    private static final Random RANDOM = new Random();
    
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
    
    // TODO: Write randomWhitespace() function
    
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

    public void testCollapseWhitespace() {
        System.out.println("collapseWhitespace");
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
