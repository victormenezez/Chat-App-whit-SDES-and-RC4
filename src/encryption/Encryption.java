package encryption;

/**
 *
 * @author victor
 */
public class Encryption {
    
    public Encryption(){}
    
    public byte[] encrypt(final byte[] plaintext){
        return plaintext;
    }
    public byte[] decrypt(final byte[] ciphertext){
        return encrypt(ciphertext);
    }
}
