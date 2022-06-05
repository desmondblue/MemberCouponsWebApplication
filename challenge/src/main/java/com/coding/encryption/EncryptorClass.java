package com.coding.encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class EncryptorClass {
/*
        Typical AES Encryption class to store credentials for Database etc. in a cryptic format
 */
    private String password;
    private String salt = "12345678";
    private IvParameterSpec iv;
    private SecretKey secret;
    private String algorithm;
    public EncryptorClass() throws NoSuchAlgorithmException, InvalidKeySpecException {

        this.password = "randompassword";
        this.algorithm = "AES/CBC/PKCS5Padding";
        this.iv = this.generateIv();
        this.secret = getKeyFromPassword();


    }
    /*
    Secret key is used for creating cipher text
     */
    public SecretKey getKeyFromPassword()
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(this.password.toCharArray(), this.salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }
    /*
    Iv is used for creating cipher text
     */
    public IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        return new IvParameterSpec(iv);
    }
    /*
    Method for encrypting plain text
     */
    public String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, this.secret, this.iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }
    /*
    Method for decrypting cipher text
     */
    public String decrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.DECRYPT_MODE, this.secret, this.iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(input));
        return new String(plainText);
    }
    public static void main (String args[]) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        System.out.println(new EncryptorClass().decrypt("m7uMxwkk1I6BZfNd8JGHMg=="));

    }
}
