package com.baselogic.tutorials.reference.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 * TODO: Need to add the following into the Signatures:
 * MAC
 * SHA-1
 * SHA-2 (256)
 * MD5
 */
public final class DigitalSignatureDemo {

	private static final Logger logger = LoggerFactory.getLogger(DigitalSignatureDemo.class);


	public static void main(String[] args) {

		/*Call the generateDigitalSignature() method of the DigitalSignatureDemo class
		 * to generate a digital signature of the text passed to the generateDigitalSignature() method */
		DigitalSignatureDemo.generateDigitalSignature("ABB statement.");


		/*Call the verifyDigitalSignature() method of the DigitalSignatureDemo class
		 * to verify a digital signature of the text passed to the verifyDigitalSignature() method */
		DigitalSignatureDemo.verifyDigitalSignature("ABB statement.");
	}


	/*
	 * This method generates a digital signature and saves the digital signature
	 * along with the public key to the file system
	 */
	static void generateDigitalSignature(String input) {
		try {

			/* Obtain input text to sign as a byte array */
			//byte[] data = "Digital Signature Demo.".getBytes("UTF8");
			byte[] data = input.getBytes("UTF8");

			/* Generate a key pair */
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(1024);

			/*
			 * Obtain the private and public keys as PrivateKey and PublicKey
			 * objects
			 */
			KeyPair pair = keyGen.generateKeyPair();

			PrivateKey priv = pair.getPrivate();
			PublicKey pub = pair.getPublic();

			/* Generate the digital signature */
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initSign(priv);
			sig.update(data);

			/* Sign the input data */
			byte[] signatureBytes = sig.sign();

			/* Save the digital signature to a file */
			FileOutputStream sigFos = new FileOutputStream("signature.sig");
			sigFos.write(signatureBytes);
			sigFos.close();
			System.out.println("Digital signature saved to: signature.sig");

			/* Save the public key to a file */
			byte[] key = pub.getEncoded();
			FileOutputStream keyFos = new FileOutputStream("pubk.key");
			keyFos.write(key);
			keyFos.close();

			System.out.println("Public key saved to: pubk.key");

		} catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}

	}

	/* This method verifies a digital signature */
	static void verifyDigitalSignature(String input) {
		try {

			/* Retrive the public key from the file system */
			FileInputStream keyFis = new FileInputStream("pubk.key");
			byte[] encKey = new byte[keyFis.available()];
			keyFis.read(encKey);
			keyFis.close();

			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

			/* Retrive the digital signature from the file system */
			FileInputStream sigFis = new FileInputStream("signature.sig");
			byte[] sigToVerify = new byte[sigFis.available()];
			sigFis.read(sigToVerify);
			sigFis.close();

			/* Verify the signature */
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(pubKey);

			byte[] data = input.getBytes("UTF8");
//			byte[] data = "Digital Signature Demo.".getBytes("UTF8");
			sig.update(data);

			boolean result = sig.verify(sigToVerify);

			System.out.println("Signature verification result: " + result);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

}
