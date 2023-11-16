package testframe.api.text;

public class PlainTextProcessor {
    
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
        boolean prevCharNotSpaceOrTab = true;
        for (int i = 0; i < len; i++) {
            char currChar = characters[i];
            boolean currCharNotSpaceOrTab = currChar != ' ' && currChar != '\t';
            if (currCharNotSpaceOrTab) {
                processed[curr] = characters[i];
                curr++;
            } else {
                if (prevCharNotSpaceOrTab) {
                    processed[curr] = ' ';
                    curr++;
                }
            }
            prevCharNotSpaceOrTab = currCharNotSpaceOrTab;
        }
        return new String(processed, 0, curr);
    }
    
    // TODO: Write tests for this
    public static boolean equalsWithWhitespaceCollapsed(String s1, String s2) {
        return true;
    }

}
