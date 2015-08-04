package com.baselogic.tutorials.reference.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import java.io.*;
import java.security.*;

/**
 *
 *
 *
 * Hybrid Cryptosystem

 Example: For a 1024 bit key, you can encrypt around 1024 / 8 = 128 bytes
 Note: Exact value is 128 bytes - 11 bytes for padding

 You can use a symmetric key to encrypt and decrypt the data (> 128 bytes) to be transferred. RSA can only encrypt data up to a certain extent (e.g. 128 bytes) which depends on the RSA key length.

 This means that if you want to transfer anything bigger than 128 bytes, you have to transfer a symmetric key < 128 bytes first so you can have the following:

 Generate a symmetric key (< 128 bytes)
 Encrypt symmetric key with RSA
 Transfer encrypted symmetric key
 Decrypt symmetric key with RSA
 Encrypt data (> 128 bytes) with symmetric key
 Transfer encrypted data
 Decrypt encrypted data with symmetric key

 or (transfer encrypted symmetric key and encrypted data at the same time)

 Generate a symmetric key (< 128 bytes)
 Encrypt symmetric key with RSA
 Encrypt data (> 128 bytes) with symmetric key
 Transfer encrypted symmetric key & encrypted data
 Decrypt symmetric key with RSA
 Decrypt encrypted data with symmetric key
 *
 * see SecretKeySpec
 */
public final class EncryptionUtilities {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtilities.class);

    public static final String ENCODING = "UTF-8";


    /**
     * Generate KeyPair
     * @param algorithmModePad
     * @param keySize
     * @return
     */
    public static KeyPair generateKeyPair(final String algorithmModePad, final int keySize) {
        KeyPair keyPair = null;

        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithmModePad);
            keyGen.initialize(keySize);

            keyPair = keyGen.generateKeyPair();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return keyPair;
    }

    /**
     * Generate SecretKey
     * @param algorithmModePad
     * @param keySize
     * @return
     */
    public static Key generateSecretKey(final String algorithmModePad, final int keySize) {
        SecretKey secretKey = null;

        try {
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithmModePad);
            keyGenerator.init(keySize);

            secretKey = keyGenerator.generateKey();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return secretKey;
    }


    /**
     *
     * @param text
     * @param algorithmModePad
     * @param key
     * @return
     */
    public static byte[] encrypt(final String algorithmModePad, final PublicKey key, final String text) {
        byte[] cipherText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            cipherText = cipher.doFinal(text.getBytes(ENCODING));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     *
     * @param originalText
     * @param algorithmModePad
     * @param key
     * @return
     */
    public static byte[] encryptWithUpdate(final String algorithmModePad, final Key key, final String originalText) {
        byte[] cipherText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            logger.info("Original Data: {}", originalText);

            // TODO: Need to get a usecase for cipher.update()
//            cipherText = cipher.update(originalText.getBytes(ENCODING));
            cipherText = cipher.doFinal(originalText.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     *
     * @param encryptedText
     * @param algorithmModePad
     * @param privateKey
     * @return
     */
    public static String decrypt(final String algorithmModePad, final PrivateKey privateKey, final byte[] encryptedText) {
        byte[] decryptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            decryptedText = cipher.doFinal(encryptedText);

        } catch (Exception ex) {
            logger.error("decrypt exception", ex);
            ex.printStackTrace();
        }

        return new String(decryptedText);
    }


    /**
     *
     * @param encryptedText
     * @param algorithmModePad
     * @param key
     * @return
     */
    public static String decryptWithUpdate(final String algorithmModePad, final Key key, final byte[] encryptedText) {
        byte[] decryptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.DECRYPT_MODE, key);

//            decryptedText = cipher.update(encryptedText);
            decryptedText = cipher.doFinal(encryptedText);

        } catch (Exception ex) {
            logger.error("decryptWithUpdate exception", ex);
            ex.printStackTrace();
        }

        return new String(decryptedText);
    }



    /**
     * Use Generics, to retrieve a PublicKey or PrivateKey
     * @param fileName
     * @return PublicKey or PrivateKey
     */
    @SuppressWarnings("unchecked")
    protected static <T> T getKeyFromFile(final String fileName) {

        ObjectInputStream inputStream = null;

        T key = null;

        try {
            inputStream = new ObjectInputStream(
                    new FileInputStream(fileName));

            // @SuppressWarnings("unchecked")
            key = (T) inputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return key;
    }

    /**
     * Save a Key to file, WITHOUT encryption
     * @param fileName
     * @param key
     */
    protected static void saveKeyToFile(final String fileName, final Key key) {

        File keyFile = new File(fileName);
        ObjectOutputStream keyOs = null;

        try {
//            keyFile = new File(fileName);
            if (keyFile.getParentFile() != null) {
                boolean mkdirsResult = keyFile.getParentFile().mkdirs();

                if(mkdirsResult){
                    throw new RuntimeException("mkdirs() failed");
                }
            }

            boolean keyFileResult = keyFile.createNewFile();
            if(! keyFileResult){
                logger.error("'{}' .createNewFile() failed", fileName);
                throw new RuntimeException("keyFile.createNewFile() failed");
            }

            keyOs = new ObjectOutputStream(
                    new FileOutputStream(keyFile));

            keyOs.writeObject(key);
            keyOs.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (keyOs != null) {
                try {
                    keyOs.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }


    /**
     * Export a SecretKey to a file.
     * @param secretKey
     * @param algorithmModePad
     * @param fileName
     */
    protected static void exportKeyWrapToFile(final SecretKey secretKey, final String algorithmModePad, final String fileName) {

        File keyFile = new File(fileName);
        DataOutputStream dos = null;

        try {

            // start
            Cipher c = Cipher.getInstance(algorithmModePad);
            c.init(Cipher.WRAP_MODE, secretKey);

            byte[] wrappedKey = c.wrap(secretKey);

            dos = new DataOutputStream(new FileOutputStream(keyFile));

            dos.write(wrappedKey, 0, wrappedKey.length);
            dos.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * Encrypt text to a CipherOutputStream and save to File.
     * @param key
     * @param algorithmModePad
     * @param fileName
     * @param text
     */
    public static void cipherOutputStreamToFile(final Key key,
                                                final String algorithmModePad,
                                                final String fileName,
                                                final String text) {
        try {
            Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            FileOutputStream fos = new FileOutputStream(fileName);

            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            PrintWriter pw = new PrintWriter(cos);

            pw.println(text);

            pw.close();
        } catch (Exception e) {
            logger.error("Error : {}", e.getMessage(), e);
        }
    }

    /**
     * Decrypt text from CipherInputStream.
     * @param key
     * @param algorithmModePad
     * @param fileName
     * @param text
     */
    public static String cipherInputStreamFromFile(final Key key,
                                                 final String algorithmModePad,
                                                 final String fileName,
                                                 final String text) {
        StringBuilder sb = new StringBuilder();

        try {
            Cipher cipher = Cipher.getInstance(algorithmModePad);
            cipher.init(Cipher.DECRYPT_MODE, key);


            // Read encrypted text from file
            FileInputStream fis = new FileInputStream(fileName);
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            BufferedReader br = new BufferedReader(new InputStreamReader(cis));


            String line;
            while ((line = br.readLine()) != null) {
                logger.info(line);
                sb.append(line);
            }
            br.close();



        } catch (Exception e) {
            logger.error("Error : {}", e.getMessage(), e);
        }
        return sb.toString();
    }




} // The End...
