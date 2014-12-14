package de.dbo.samples.crypto0.keystore;

import static de.dbo.samples.crypto0.keystore.KeyStoreConfig.*;
import static de.dbo.samples.crypto0.keystore.X509CertificateGenerator.generateCertificate;
import static de.dbo.samples.crypto0.keystore.KeyGenerator.generateKeyPairUsingKeystore;
import static de.dbo.samples.crypto0.keystore.Utils.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * http://stackoverflow.com/questions/5312559/how-do-i-programmatically-create-a-new-keystore
 * 
 * @author Dmitri Boulanger, Hombach
 *
 *         D. Knuth: Programs are meant to be read by humans and only
 *         incidentally for computers to execute
 * 
 */

public class KeyStoreManager {
	private static final Logger log = LoggerFactory.getLogger(KeyStoreManager.class);
	
	public static KeyStore defaultSingleKeystore(final String keyAlias
			, final char[] keystorePassword, final char[] keyPassword) throws Exception{
		final KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(null, keystorePassword);
		final Provider provider = keystore.getProvider();
		final KeyPair keyPair = generateKeyPairUsingKeystore(provider, KEY_PAIR_GENERATOR_NAME);
		final Certificate certificate =  generateCertificate(keyPair);    
		final Certificate[] certificateChain = new Certificate[1];  
		certificateChain[0] = certificate;  
		keystore.setKeyEntry(keyAlias, keyPair.getPrivate(), keyPassword, certificateChain);
		keystore.setCertificateEntry(CERTIFICATE_NAME, certificate);
		return keystore;
	}

	private final char[] keystorePassword;
	private final String keystoreFilepath;
	private final char[] keyPassword;

	private KeyStore keystore = null;
	
	/**
	 * 
	 * @param keystoreFilepath path of the key-store file
	 * @param keystorePassword password of the key-store
	 * @param keyPassword password of the key-store of the default key
	 * 
	 * @throws Exception
	 */
	public KeyStoreManager(final String keystoreFilepath
			, final String  keystorePassword, final String  keyPassword) throws Exception {
		
		this.keystorePassword = keystorePassword.toCharArray();
		this.keyPassword = keyPassword.toCharArray();
		
		this.keystoreFilepath = new File(keystoreFilepath).getCanonicalPath();
		if (new File(keystoreFilepath).exists()) {
			log.info("Key-store exists. File=[" + this.keystoreFilepath+"]");
			return;
		}
		
		persistNewDefaultKeystore();
	}
	
	void persistNewDefaultKeystore() throws Exception {
		log.info("Creating new empty key-store ...");
		if (!new File(keystoreFilepath).createNewFile()) {
			throw new IOException("Can't create new key-store file " + keystoreFilepath);
		}

		final KeyStore defaultKeystore = defaultSingleKeystore(KEY_NAME,keystorePassword, keyPassword);
		save(defaultKeystore); // done. Store away the key-store
	}

	/** 
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SignatureException 
	 * @throws NoSuchProviderException 
	 * @throws IllegalStateException 
	 * @throws InvalidKeyException 
	 * 
	 * @see KeyStoreManager#KEYSTORE_PASSWORD
	 * @see KeyStoreManager#KEYSTORE_FILEPATH
	 */
	public KeyStoreManager() throws Exception {
		this(KEYSTORE_FILEPATH, KEYSTORE_PASSWORD, KEY_PASSWORD);
	}
	
	public KeyStoreManager(final String keystoreFilepath) throws Exception {
		this(keystoreFilepath,  KEYSTORE_PASSWORD, KEY_PASSWORD);
	}
		
	public String getKeystoreFilepath() {
		return keystoreFilepath;
	}
	
	public KeyStore getKeystore() {
		return keystore;
	}

	public void save() throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException {
		final FileOutputStream fos = new FileOutputStream(keystoreFilepath);
		keystore.store(fos, keystorePassword);
		fos.close();
	}

	/**
	 * 
	 * @param keystore
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	private void save(final KeyStore keystore) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		final FileOutputStream fos = new FileOutputStream(keystoreFilepath);
		keystore.store(fos, keystorePassword);
		fos.close();
	}

	public void loadKeystore() throws Exception {
		if (null != keystore) {
			log.info("Existing key-store from [" + keystoreFilepath+ "]: "
					+ "\n\t - type: " + keystore.getType()
					+ "\n\t - provider: " + keystore.getProvider().getInfo()
					);
			return;
		}
		log.info("Loading key-store from [" + keystoreFilepath+ "] ...");
		try {
			keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		} catch (KeyStoreException e) {
			throw new Exception(
					"Can't get instance of the default key-store: ", e);
		}

		final FileInputStream fis;
		try {
			fis = new FileInputStream(keystoreFilepath);
		} catch (Exception e) {
			throw new Exception("Cannnot get input stream from file "
					+ keystoreFilepath, e);
		}
		try {
			keystore.load(fis, keystorePassword);
		} catch (Exception e) {
			throw new Exception("Failure loading key-store from file "
					+ keystoreFilepath, e);
		}
		log.info("Key-store loaded from [" + keystoreFilepath+"]: " + print(keystore));
		log.info("Provider from the key-store: ", print(keystore.getProvider()));
	}

	/**
	 * 
	 * @param keyName
	 *            the name of the entity in the key-store for which information
	 *            is desired
	 * @param keyPassword
	 *            the keystorePassword that was used to encrypt the private key
	 * @throws Exception
	 */
	public boolean lookupKey(final String keyName, final String keyPassword) throws Exception {
		log.info("Key=[" + keyName + "] ...");
		if (keystore.isKeyEntry(keyName)) {
			log.info("Key=[" + keyName + "] is a key-entry in the keystore");
			final char c[] = new char[keyPassword.length()];
			keyPassword.getChars(0, c.length, c, 0);
			final Key key;
			try {
				key = keystore.getKey(keyName, c);
			} catch (Exception e) {
				log.error("Bad key-password? ", e);
				return false;
			}
			log.info("Key=[" + keyName + "] has private key ["+ key+"] Algorithm: " + key.getAlgorithm());
			final Certificate certs[] = keystore.getCertificateChain(keyName);
			log.info("Key=[" + keyName + "] has certificate-chain with size "+ certs.length);
			boolean ret = false;
			if (certs[0] instanceof X509Certificate) {
				X509Certificate x509 = (X509Certificate) certs[0];
				log.info("Key=[" + keyName + "] is really "+ x509.getSubjectDN());
				ret = true;
			} 
			if (certs[certs.length - 1] instanceof X509Certificate) {
				X509Certificate x509 = (X509Certificate) certs[certs.length - 1];
				log.info("Key=[" + keyName + "] was verified by " + x509.getIssuerDN());
				ret = ret && true;
			}
			return ret;
		} 
		else {
			log.warn("Key=[" + keyName + "] is unknown to this keystore");
			return false;
		}
	}
	
	public boolean lookupCertificate(final String cerrtificateName)  throws Exception {
		if (keystore.isCertificateEntry(cerrtificateName)) {
			log.info("Certificate=[" + cerrtificateName + "] is a certificate-entry in the keystore");
			final Certificate certificate = keystore.getCertificate(cerrtificateName);
			if (certificate instanceof X509Certificate) {
				final X509Certificate x509 = (X509Certificate) certificate;
				log.info("Certificate=[" + cerrtificateName + "] is really " + x509.getSubjectDN());
				log.info("Certificate=[" + cerrtificateName + "] was verified by " + x509.getIssuerDN());
				return true;
			}
		} 
		else {
			log.warn("Certificate=[" + cerrtificateName + "] is unknown to this keystore");
		}
		return false;
	}
}
