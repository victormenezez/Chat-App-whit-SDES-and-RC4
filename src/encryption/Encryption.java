/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
