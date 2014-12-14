package de.dbo.samples.crypto0.keystore;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class KeyGenerator {
	private static final Logger log = LoggerFactory
			.getLogger(KeyGenerator.class);

	public static final String GENERATOR_NAME = "KeyPairGenerator.DSA";

	static final KeyPair generateKeyPairUsingKeystore(final KeyStore keystore)
			throws KeyStoreException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		final Provider provider = keystore.getProvider();
		final String generatorClassname = (String) provider.get(GENERATOR_NAME);
		log.info("KeyPair-generation classname: " + generatorClassname);
		final KeyPairGenerator keyPairGenerator = (KeyPairGenerator) Class.forName(generatorClassname).newInstance();
		log.info("KeyPair-generation algorithm: " + keyPairGenerator.getAlgorithm());
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		log.info("Key-pair generated:" 
				+ "\nPUBLIC  " + keyPair.getPublic().toString() 
				+ "\nPRIVATE " + keyPair.getPrivate().toString());
		return keyPair;
	}
}
