package org.testframe.api.text;

public class PlainTextProcessor {
    
    // TODO: Write tests for this
    public static char lowestUnassignedUnicodeCharacter() {
        return '?';
    }
    
    /**
     * Collapses consecutive tabs and spaces in a <code>String</code> to single 
     * spaces.
     * @param s The <code>String</code> from which to collapse out consecutive 
     * tabs and spaces. For example, 
     * "Hello,&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;world!"
     * @return A <code>String</code> with all characters the same as 
     * <code>s</code> except with the consecutive spaces and tabs collapsed to 
     * single spaces. For example, "Hello, world!"
     */
    public static String collapseWhitespace(String s) {
        char[] characters = s.toCharArray();
        int len = characters.length;
        char[] processed = new char[len];
        int curr = 0;
        boolean prevCharNotWhitespace = true;
        for (int i = 0; i < len; i++) {
            char currChar = characters[i];
            boolean currCharNotWhitespace = !Character.isWhitespace(currChar);
            if (currCharNotWhitespace) {
                processed[curr] = characters[i];
                curr++;
            } else {
                if (prevCharNotWhitespace) {
                    processed[curr] = ' ';
                    curr++;
                }
            }
            prevCharNotWhitespace = currCharNotWhitespace;
        }
        return new String(processed, 0, curr);
    }
    
    // TODO: Write tests for this
    public static boolean equalsWithWhitespaceCollapsed(String s1, String s2) {
        return true;
    }

}
