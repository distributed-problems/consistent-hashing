package consistenthashing;

/**
 * Interface for calculating hash function.
 */
public interface HashFunction {
    /**
     * Compute hash function for given key.
     *
     * @param key
     * @return
     */
    long hash(String key);
}
