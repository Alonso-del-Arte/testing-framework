package org.example.demo.secrets;

/**
 * Holds API keys as label-value pairs.
 * @author Alonso del Arte
 */
abstract class APIKeyStore {
    
    /**
     * Adds a label value pair to the key store.
     * @param label A label for the API key. For example, "Acme Widget API key".
     * @param value The value of the API key. For example, "EXAMPLE55500000001".
     * @return True if only if the key that wasn't already stored was 
     * successfully added to the store. False if the key was already in the 
     * store or if there was some problem adding the key (though in the latter 
     * case, an exception might be preferable). 
     * @throws RuntimeException If there was some problem adding the key to the 
     * store. 
     */
    public abstract boolean add(String label, String value);
    
    public abstract String get(String label);

}
