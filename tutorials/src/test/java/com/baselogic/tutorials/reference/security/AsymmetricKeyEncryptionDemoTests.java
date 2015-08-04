package com.baselogic.tutorials.reference.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by mickknutson on 4/30/15.
 */
public final class AsymmetricKeyEncryptionDemoTests {

    private static final Logger logger = LoggerFactory.getLogger(AsymmetricKeyEncryptionDemoTests.class);

    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
    @Before
    public void beforeEachTest() throws Exception {
    }

    @After
    public void afterEachTest() throws Exception {
    }

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void asymmetricKeyEncryptionDemoTest() throws Exception {
        /* Check if key is present */

        if (!AsymmetricKeyEncryptionDemo.areKeysPresent()) {
            AsymmetricKeyEncryptionDemo.generateKeyAndSaveToFile();
        }

        /* Obtain the public key as a PublicKey object */
        final String originalText = "Text to be encrypted.";

        final PublicKey publicKey = EncryptionUtilities.getKeyFromFile(AsymmetricKeyEncryptionDemo.PUBLIC_KEY_FILE);
        final PrivateKey privateKey = EncryptionUtilities.getKeyFromFile(AsymmetricKeyEncryptionDemo.PRIVATE_KEY_FILE);

        /* Perform encryption with the PublicKey object */
        final byte[] cipherText = AsymmetricKeyEncryptionDemo.encrypt(originalText, publicKey);

        /* Perform decryption with the PrivateKey object */
        final String decryptedText = AsymmetricKeyEncryptionDemo.decrypt(cipherText, privateKey);

			/* Print out the original, encrypted, and decrypted data */
        logger.info("Original Data: {}", originalText);
        assertThat(originalText, is(originalText));

        logger.info("Encrypted Data: {}", cipherText);
        assertThat(cipherText.length, is(256));

        logger.info("Decrypted Data: {}", decryptedText);
        assertThat(decryptedText, is(originalText));

    }

}