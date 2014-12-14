package de.dbo.samples.crypto0.keystore;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyGenerator {
	private static final Logger log = LoggerFactory.getLogger(KeyGenerator.class);
	
	public static final KeyPair generateKeyPairUsingKeystore(final KeyStore keystore) 
			throws KeyStoreException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	final KeyPairGenerator keyPairGenerator =
			(KeyPairGenerator)  Class.forName( (String) keystore.getProvider().get("KeyPairGenerator.DSA") ).newInstance();
	
	log.info("KeyPair-generation algorithm: "  + keyPairGenerator.getAlgorithm());
	System.out.println(keyPairGenerator.getAlgorithm());
	final KeyPair keyPair =  keyPairGenerator.generateKeyPair();
	log.info("Key-pair generated with " + keyPairGenerator.getClass().getName()+":"
			+ "\n\t public " + keyPair.getPublic().toString()
			+ "\n\t private " + keyPair.getPrivate().toString());
	return keyPair;
}
}
