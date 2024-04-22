package org.testframe.api.random;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class ExpandedRandom extends Random {

    private static final long serialVersionUID = 4553887127401760416L;
    
    public abstract int nextPowerOfTwo();
    
    public abstract CoinSide flipCoin();
    
    public abstract char nextASCIIChar();
    
    public abstract String nextASCIICharSeq(int length);
    
    public abstract String nextASCIICharSeq(int minLength, int maxLength);
    
    public abstract String nextString();
    
    public abstract OffsetDateTime nextDateTime();
    
    public abstract <E> E nextObject(E[] array);

    public abstract <E> E nextObject(List<E> list);

    public abstract <E> E nextObject(Set<E> set);

}
