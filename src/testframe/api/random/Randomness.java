package testframe.api.random;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
// TODO: new URL, cast URL.getContent() to InputStream for Scanner,

public class Randomness {
    
    // TODO: Decouple from random.org
    //https://www.random.org/integers/?num=1&min=-536870912&max=536870911&col=1&base=10&format=plain&rnd=new
    static int[] connectToRandomDotOrg() throws IOException {
        int[] array = {0};
        return array;
    }
    
    private static int[] integers;
    
    static {
        try {
            integers = connectToRandomDotOrg();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
    
    // TODO: Write tests for this
    static int nextInt() {
        return 0;
    }

}
