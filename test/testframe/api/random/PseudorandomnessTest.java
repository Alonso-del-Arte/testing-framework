package testframe.api.random;

import java.io.IOException;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class PseudorandomnessTest {
    
    private static class OfflinePseudorandomness extends Pseudorandomness {
        
        @Override
        int[] connectToExternalRandomnessProvider() throws IOException {
            String excMsg = "Simulating the Internet connection is unavailable";
            throw new IOException(excMsg);
        }
        
    }

}
