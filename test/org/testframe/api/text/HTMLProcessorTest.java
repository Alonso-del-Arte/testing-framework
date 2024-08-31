package org.testframe.api.text;

import java.util.Arrays;

import static org.testframe.api.Asserters.*;
import org.testframe.api.Test;
import static org.testframe.api.text.PlainTextProcessorTest.RANDOM;

public class HTMLProcessorTest {
    
    private static char chooseBMPChar() {
        char ch = (char) RANDOM.nextInt(Short.MAX_VALUE);
        while (!Character.isDefined(ch)) {
            ch++;
        }
        return ch;
    }
    
    @Test
    public void testConformCharacterEntities() {
        System.out.println("conformCharacterEntities");
        int len = RANDOM.nextInt(16) + 4;
        char[] array = new char[len];
        char ch = chooseBMPChar();
        Arrays.fill(array, ch);
        String expected = new String(array);
        String entityDecimal = "&#" + Integer.toString(ch) + ';';
        String entityHexadecimal = "&#x" + Integer.toHexString(ch) + ';';
        String[] choices = {"" + ch, entityDecimal, entityHexadecimal};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(choices[i % 3]);
        }
        String s = builder.toString();
        String actual = HTMLProcessor.conformCharacterEntities(s);
        String msg = "Conforming \"" + s + "\"";
        assertEquals(expected, actual, msg);
    }

}
