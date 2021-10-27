package org.example.exercises.collections.mutable;

public abstract class LRUCache<N, V> {
    
    public static final int MINIMUM_CAPACITY = 4;
    
    public static final int MAXIMUM_CAPACITY = 128;
    
    /**
     * Creates a value for a given name. Ideally this function
     * should only be called by {@link #retrieve(java.lang.Object)
     * retrieve()}.
     * @param name The name to create a value for. Once the value is
     * in the cache, this name can be used to retrieve it.
     * @return A new value. Preferably not null.
     */
    protected abstract V create(N name);
    
    // TODO: Write a test for this
    boolean has(V value) {
        return false;
    }
    
    // TODO: Write a test for this
    public V retrieve(N name) {
        return null;
    }
    
    public LRUCache(int size) {
        // TODO: Throw exception if size < MINIMUM_CAPACITY
        // TODO: Throw exception if size > MAXIMUM_CAPACITY
        // TODO: Initialize queue
    }
    
}
