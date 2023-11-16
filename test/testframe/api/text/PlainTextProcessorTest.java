package testframe.api.text;

import java.util.Random;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class PlainTextProcessorTest {
    
    private static final Random RANDOM = new Random();
    
    private static String randomWhitespace() {
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
    
    public void testCollapseWhitespace() {
        System.out.println("collapseWhitespace");
//        
//        
//        
//        
    }

}
