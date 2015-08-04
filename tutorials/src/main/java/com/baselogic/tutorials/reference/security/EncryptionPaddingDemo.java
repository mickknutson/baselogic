package com.baselogic.tutorials.reference.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * For ECB and CBC modes of operation, each block (128 bits or 16 bytes for AES)
 * of plaintext or will be encrypted once it is available. Furthermore, your
 * example uses PKCS#7 padding (or, in Java, "PKCS5Padding") to make sure that
 * the last plaintext is padded to a full block. Otherwise you would not be able to encrypt it.
 * <p/>
 * When you encrypt it is obviously not a good idea to buffer all the resulting ciphertext.
 * So update will simply return all blocks it can encrypt. Therefore you will always get a multiple
 * of the blocksize back. That means that it may keep part of a block in an internal buffer.
 * Of course, once you add enough bytes for a full block it will encrypt and return the data.
 * All this means that the update may return up to blocksize - 1 bytes more or less than the input.
 * For smaller input it may not return anything at all - everything is buffered until the block is full.
 * <p/>
 * Now PKCS#7 padding is always applied, adding 1 to blocksize bytes.
 * Obviously the cipher must know if more bytes are to be expected, or if the end of the plaintext is reached,
 * before it applies the padding. So doFinal will return at least one block, even if no data is presented to it.
 * If the previous updates did not return any data, then the doFinal() method will return the entire ciphertext.
 * <p/>
 * More or less the same reasoning can be performed for decryption. Also note the getOutputSize(int inputLength)
 * and the getBlockSize() methods of Cipher. If you want to receive all the data back at once, you can use a
 * stream cipher mode of operation, such as "AES/CTR/NoPadding", which has a block size of 1 byte.
 *
 *
 * "AES" for Cipher will use the provider's default.
 *
 * For the SUN provider, that would be "AES/ECB/PKCS5Padding".
 * You should use at least "AES/CBC/PKCS5Padding" (requires a random IV)
 * or "AES/CTR/NoPadding" (requires a unique IV).
 *
 * Always specify the mode of operation and padding explicitly.
 *
 * And never use getBytes() without specifying a character encoding
 *
 * Electronic Codebook (ECB)
 * Cipher Block Chaining (CBC)
 * Propagating Cipher Block Chaining (PCBC)
 *
 * <b>Cipher Feedback (CFB)</b>
 * CFB shares two advantages over CBC mode with the stream cipher modes OFB and CTR: the block cipher
 * is only ever used in the encrypting direction, and the message does not need to be padded to a multiple
 * of the cipher block size (though ciphertext stealing can also be used to make padding unnecessary).
 *
 * Output Feedback (OFB)
 *
 * <b>Counter (CTR)</b>
 * Note: CTR mode (CM) is also known as integer counter mode (ICM) and segmented integer counter (SIC) mode.
 * Like OFB, Counter mode turns a block cipher into a stream cipher.
 *
 * TODO:
 * Look into Ciphertext stealing (CTS)
 *
 *
 * @see <a href="http://en.wikipedia.org/wiki/Block_cipher_mode_of_operation">Block_cipher_mode_of_operation</a>
 *
 */
public final class EncryptionPaddingDemo {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionPaddingDemo.class);

    public static final String ALGORITHM = "AES";
    public static final String MODE = "ECB";
    public static final String PAD = "PKCS7Padding";

    public static final String ALGORITHM_MODE_PAD = "AES/ECB/PKCS7Padding";
//    public static final String ALGORITHM_MODE_PAD = "AES/CBC/PKCS7Padding";
//    public static final String ALGORITHM_MODE_PAD = "AES/CTR/NoPadding";

    public byte[] encryptSecretKeySpec(final byte[] data, final byte[] keyPass, final String algorithmModePad)
            throws  NoSuchPaddingException,
                    NoSuchAlgorithmException,
                    InvalidKeyException,
                    BadPaddingException,
                    IllegalBlockSizeException,
                    UnsupportedEncodingException {

        Cipher cipher = Cipher.getInstance("AES");

        SecretKeySpec AESkeySpec = new SecretKeySpec(keyPass, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, AESkeySpec);

//        cipher.update(s.getBytes(Charset.defaultCharset()));
        cipher.update(data);

        byte[] encryptedData = cipher.doFinal();

        return encryptedData;
    }
    /*
    The update method returns encrypted block. But as I see you take the ciphertext only from doFinal.
    You supposed to use all blocks from update and from the doFinal for completed encrypted stream of blocks.

    Note: you are using ECB mode, it means each block encrypted independently, it is less secure and should
    be used only if you know what are you doing. That's why you are able to decrypt the last block (16 bytes for AES)
    and see truncated plaintext. More info: http://en.wikipedia.org/wiki/Block_cipher_mode_of_operation
     */
}