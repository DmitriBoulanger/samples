package de.dbo.samples.crypto0.keystore;

import static de.dbo.samples.crypto0.keystore.KeyStoreConfig.KEYSTORE_PASSWORD;
import static de.dbo.samples.crypto0.keystore.KeyStoreConfig.KEY_NAME;
import static de.dbo.samples.crypto0.keystore.KeyStoreConfig.KEY_PASSWORD;
import static de.dbo.samples.crypto0.keystore.KeyStoreConfig.CERTIFICATE_NAME;

import java.io.File;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class KeyGeneratorjunit {
	private static final Logger log = LoggerFactory.getLogger(KeyGeneratorjunit.class);
	
	/**
	 * tests a default instance of the KeyStoreManager
	 */
	@Test
	public void defaultExistigKeyStore() {
		final KeyStoreManager keyStoreManager;
		try {
			keyStoreManager = new KeyStoreManager();
		} catch (Exception e) {
			log.error("Failure creating KeyStore manager", e);
			fail("Cannot create KeyStoreManager: " + e.toString());
			return;
		}
	    testIt(keyStoreManager);
	}
	
	/**
	 * tests an instance of the KeyStoreManager
	 * using non-default constructor with default parameters
	 */
	@Test
	public void defaultNewKeyStore() {
		final String keystorePath = 
				System.getProperty("user.dir") + File.separator + ".keystore" 
						+ "_" + UUID.randomUUID().toString();
		final KeyStoreManager keyStoreManager;
		try {
			keyStoreManager = new KeyStoreManager(keystorePath, KEYSTORE_PASSWORD, KEY_PASSWORD);
		} catch (Exception e) {
			log.error("Failure creating KeyStore manager", e);
			fail("Cannot create KeyStoreManager: " + e.toString());
			return;
		}
	    testIt(keyStoreManager);
	}
	
	/**
	 * tests an instance of the KeyStoreManager
	 * using constructor with custom location of the key-store
	 */
	@Test
	public void customNewKeystore() {
		final String keystorePath = 
				System.getProperty("user.dir") + File.separator + ".keystore" 
						+ "_" + UUID.randomUUID().toString();
		final KeyStoreManager keyStoreManager;
		try {
			keyStoreManager = new KeyStoreManager(keystorePath);
		} catch (Exception e) {
			log.error("Failure creating KeyStore manager", e);
			fail("Cannot create KeyStoreManager: " + e.toString());
			return;
		}
	    testIt(keyStoreManager);
	}
	
	private void testIt(final KeyStoreManager keyStoreManager) {
		testIt(keyStoreManager, KEY_NAME, KEY_PASSWORD);
	}
	
	private void testIt(final KeyStoreManager keyStoreManager,
			final String keyName, final String keyPassword) {
		final File keystoreFile = new File(keyStoreManager.getKeystoreFilepath());
		assertTrue("Keystore-file doesn't exist", keystoreFile.exists());
		
		try {
			keyStoreManager.loadKeystore();
		} catch (Exception e) {
			log.error("Failure loading key-store:", e);
			fail("Cannot load key-store:  " + e.toString());
			return;
		}
		
		final KeyStore keystore =  keyStoreManager.getKeystore();
		assertNotNull("Key-store is null", keystore);
		
		final int keystoreSize;
		try {
			keystoreSize = keystore.size();
		} catch (KeyStoreException e) {
			log.error("Failure getting key-store size", e);
			fail("Cannot get key-store size: " + e.toString());
			return;
		}
		// at least a key and a certificate are expected
		assertTrue("Key-store is empty", keystoreSize>=2); 
		
		try {
			assertTrue("Key-store has no expected key-entry", keystore.isKeyEntry(keyName));
		} catch (KeyStoreException e) {
			log.error("Failure checking key-entry in key-store", e);
			fail("Cannot check key-entry in key-store: " + e.toString());
			return;
		}
		
		try {
			assertTrue("Key-store has no expected certificate-entry", keystore.isCertificateEntry(CERTIFICATE_NAME));
		} catch (KeyStoreException e) {
			log.error("Failure checking certificate-entry in key-store", e);
			fail("Cannot check certificate-entry in key-store: " + e.toString());
			return;
		}
		
		final boolean keyVerified;
		try {
			keyVerified = keyStoreManager.lookupKey(keyName, keyPassword);
		} catch (Exception e) {
			log.error("Failure looking-up key in key-store", e);
			fail("Cannot look-up key in key-store: " + e.toString());
			return;
		}
		assertTrue("Key is not verified", keyVerified);
		
		final boolean certificateVerified;
		try {
			certificateVerified = keyStoreManager.lookupCertificate(CERTIFICATE_NAME);
		} catch (Exception e) {
			log.error("Failure looking-up certificate in key-store", e);
			fail("Cannot look-up certificate in key-store: " + e.toString());
			return;
		}
		assertTrue("Certificate is not verified", certificateVerified);
	}
}
