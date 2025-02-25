package org.testframe.api.text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.testframe.api.Asserters.*;
import org.testframe.api.Test;
import static org.testframe.api.text.PlainTextProcessorTest.RANDOM;

public class HTMLProcessorTest {
    
    private static Map<String, Character> CURRENCY_ENTITIES_MAP 
            = new HashMap<>();
    
    static {
        CURRENCY_ENTITIES_MAP.put("&cent;", '\u00A2');
        CURRENCY_ENTITIES_MAP.put("&pound;", '\u00A3');
        CURRENCY_ENTITIES_MAP.put("&yen;", '\u00A5');
        CURRENCY_ENTITIES_MAP.put("&euro;", '\u20AC');
    }
    
    private static char chooseBMPChar() {
        char ch = (char) RANDOM.nextInt(Short.MAX_VALUE);
        while (!Character.isDefined(ch)) {
            ch++;
        }
        return ch;
    }
    
    @Test
    public void testStringUnchangedIfNoCharacterEntities() {
        int len = RANDOM.nextInt(32) + 8;
        char[] characters = new char[len];
        for (int i = 0; i < len; i++) {
            characters[i] = chooseBMPChar();
        }
        String intermediate = new String(characters);
        String expected = intermediate.replace("&#", "__");
        String actual = HTMLProcessor.conformCharacterEntities(expected);
        assertEquals(expected, actual);
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
