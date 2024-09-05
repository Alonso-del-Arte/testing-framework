package org.testframe.api.text;

public class HTMLProcessor {
    
    // TODO: Write tests for this
    public static String conformCharacterEntities(String s) {
        boolean keepGoing = true;
        int ampHashIndex = 0;
        while (keepGoing) {
            ampHashIndex = s.indexOf("&#", ampHashIndex);
            boolean ampHashFound = ampHashIndex > -1;
            if (ampHashFound) {
                int numVal;
                int semicolonIndex = s.indexOf(';', ampHashIndex);
                ampHashIndex += 2;
                String numStr = s.substring(ampHashIndex, semicolonIndex);
                if (numStr.startsWith("x")) {
                    numVal = Integer.parseInt(numStr.substring(1), 16);
                } else {
                    numVal = Integer.parseInt(numStr);
                }
                String target = "&#" + numStr + ';';
                String ch = "" + (char) numVal;
                s = s.replace(target, ch);
            } else {
                keepGoing = false;
            }
        }
        return s;
    }

}
