package org.example.exercises.collections.mutable;

import java.util.Random;
import java.util.regex.Pattern;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class LRUCacheTest {

    private static final int DEFAULT_SIZE = 7;

    private static final Random RANDOM = new Random();

    @Test
    public void testAddToCache() {
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String expected = "^[a-zA-Z0–9+_.-]+@[a-zA-Z0–9.-]+$";
        String actual = cache.retrieve(expected).pattern();
        assertEquals(expected, actual);
    }
    
    private static class LRUCacheImpl extends LRUCache<String, Pattern> {

        int createCallCount = 0;

        @Override
        protected Pattern create(String name) {
            createCallCount++;
            return Pattern.compile(name);
        }

        LRUCacheImpl(int size) {
            super(size);
        }

    }

}
