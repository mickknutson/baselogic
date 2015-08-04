package com.baselogic.tutorials.reference.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;

/**
 *
 */
public final class AsymmetricKeyEncryptionDemo {

    private static final Logger logger = LoggerFactory.getLogger(AsymmetricKeyEncryptionDemo.class);

    /* Define constants for algorithm and key files */
    public static final String ALGORITHM = "RSA";
    public static final String ALGORITHM_MODE_PAD = "AES/ECB/PKCS5Padding";
//    public static final String ALGORITHM_MODE_PAD = "AES/CBC/PKCS5Padding";
//    public static final String ALGORITHM_MODE_PAD = "AES/CTR/NoPadding";

    public static final String PRIVATE_KEY_FILE = "target/private.key";
    public static final String PUBLIC_KEY_FILE = "target/public.key";

    public static final int KEY_SIZE = 2048;


    /* This method generates a key pair and saves it to the file system */
    public static void generateKeyAndSaveToFile() {

        final KeyPair keyPair = EncryptionUtilities.generateKeyPair(ALGORITHM, KEY_SIZE);

        // Delete the key files first.
        new File(PRIVATE_KEY_FILE).delete();
        new File(PUBLIC_KEY_FILE).delete();

        EncryptionUtilities.saveKeyToFile(PRIVATE_KEY_FILE, keyPair.getPrivate());
        EncryptionUtilities.saveKeyToFile(PUBLIC_KEY_FILE, keyPair.getPublic());
    }

    public static boolean areKeysPresent() {

        File privateKey = new File(PRIVATE_KEY_FILE);
        File publicKey = new File(PUBLIC_KEY_FILE);

        if (privateKey.exists() && publicKey.exists()) {
            return true;
        }
        return false;
    }

    /* This method performs encryption */
    public static byte[] encrypt(final String text, final PublicKey publicKey) {
        byte[] cipherText = EncryptionUtilities.encrypt(ALGORITHM, publicKey, text);

        return cipherText;
    }

    /* This method performs decryption */
    public static String decrypt(final byte[] text, final PrivateKey privateKey) {
        String decryptedText = EncryptionUtilities.decrypt(ALGORITHM, privateKey, text);

        return decryptedText;
    }


}
