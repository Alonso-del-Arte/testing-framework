package testframe.api.random;import java.io.InputStream;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;

class Pseudorandomness extends Random {

    private static final long serialVersionUID = 4553879963396210688L;
    
    static final int REFRESH_INTERVAL = 100;
    
    private int[] integers = {};
    
    int[] connectToExternalRandomnessProvider() throws IOException {
        int[] numbers = {};
        return numbers;
    }

}
