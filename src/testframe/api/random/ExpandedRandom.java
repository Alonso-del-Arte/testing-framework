package testframe.api.random;

import java.time.OffsetDateTime;
import java.util.Random;

public abstract class ExpandedRandom extends Random {

    private static final long serialVersionUID = 4553887127401760416L;
    
    public abstract String nextString();
    
    public abstract OffsetDateTime nextDateTime();

}
