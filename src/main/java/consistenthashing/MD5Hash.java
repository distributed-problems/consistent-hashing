package consistenthashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 hash function implementation.
 */
public class MD5Hash implements HashFunction {
    private MessageDigest instance;

    public MD5Hash() throws NoSuchAlgorithmException {
        instance = MessageDigest.getInstance("MD5");
    }

    @Override
    public long hash(String key) {
        instance.reset();
        instance.update(key.getBytes());
        byte[] digest = instance.digest();

        long hash = 0;
        for(int i=0; i<4; i++) {
            hash <<=8;
            hash |= ((int)digest[i]) & 0xFF;
        }
        return hash;
    }
}
