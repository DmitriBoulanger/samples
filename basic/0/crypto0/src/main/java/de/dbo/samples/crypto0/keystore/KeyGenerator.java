package de.dbo.samples.crypto0.keystore;

import static de.dbo.samples.crypto0.keystore.Utils.print;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Key-pair (public and private pair) generator.
 * 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
final class KeyGenerator {
	private static final Logger log = LoggerFactory
			.getLogger(KeyGenerator.class);

	/**
	 * 
	 * @param provider
	 * @param generator name of the generator that is available in the provider
	 * @return key-pair
	 * 
	 * @throws KeyStoreException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	static final KeyPair generateKeyPairUsingKeystore(final Provider provider
			, final String generator)
			throws KeyStoreException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		log.debug("Using generator " + generator+ " for provider: " + print(provider));
		final String generatorClassname = (String) provider.get(generator);
		log.info("KeyPair-generation classname: " + generatorClassname);
		final KeyPairGenerator keyPairGenerator = (KeyPairGenerator) Class.forName(generatorClassname).newInstance();
		log.info("KeyPair-generation algorithm: " + keyPairGenerator.getAlgorithm());
		final KeyPair keyPair = keyPairGenerator.generateKeyPair();
		log.debug("Key-pair generated:" 
				+ "\nPUBLIC  " + keyPair.getPublic().toString() 
				+ "\nPRIVATE " + keyPair.getPrivate().toString());
		return keyPair;
	}
}
