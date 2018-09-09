/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author victor
 */
public class RC4 {
    private final byte[] S = new byte[256];
    private final byte[] T = new byte[256];
    private final int keylen;

    public RC4(final byte[] key) {
        if (key.length < 1 || key.length > 256) {
            throw new IllegalArgumentException(
                    "a chave deve ter entre 1 e 256 bytes");
        } else {
            keylen = key.length;
            for (int i = 0; i < 256; i++) {
                S[i] = (byte) i;
                T[i] = key[i % keylen];
            }
            int j = 0;
            byte tmp;
            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + T[i]) & 0xFF;
                tmp = S[j];
                S[j] = S[i];
                S[i] = tmp;
            }
        }
    }

    public byte[] encrypt(final byte[] plaintext) {
        final byte[] ciphertext = new byte[plaintext.length];
        int i = 0, j = 0, k, t;
        byte tmp;
        for (int counter = 0; counter < plaintext.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            tmp = S[j];
            S[j] = S[i];
            S[i] = tmp;
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            ciphertext[counter] = (byte) (plaintext[counter] ^ k);
        }
        return ciphertext;
    }

    public byte[] decrypt(final byte[] ciphertext) {
        return encrypt(ciphertext);
    }
 
    public static void main(String args[]) throws Exception {
//        String keyword = "hello";
//        byte[] keytest = keyword.getBytes(); //convert keyword to byte
//
//        String texto = "aloha, porra";
//        byte[] text = texto.getBytes(); // text as 12345
//        System.out.print("\nTexto original: ");
//        for(byte b: text)
//            System.out.print(b);
//        
//        RC4 rc4 = new RC4(keytest);
//
//        byte[] encrypt_text = rc4.encrypt(text);
//        System.out.print("\nTexto encriptado: ");
//        for(byte b: encrypt_text)
//            System.out.print(b);
//        
//        rc4 = new RC4(keytest);
//        byte[] backtext = rc4.encrypt(encrypt_text);
//        System.out.print("\nTexto decriptado: ");
//        for(byte b: backtext)
//            System.out.print(b);
//        
//        System.out.print("\n"+new String(backtext, StandardCharsets.UTF_8));
    }
}
