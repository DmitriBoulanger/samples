package de.dbo.samples.crypto0.keystore;

import static de.dbo.samples.crypto0.keystore.CertificateGenerator.generateCertificate;
import static de.dbo.samples.crypto0.keystore.KeyGenerator.generateKeyPairUsingKeystore;
import static de.dbo.samples.crypto0.keystore.Utils.sb;

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

	public static final String KEY_NAME = "TestKey";
	public static final String KEY_PASSWORD = "key_Password";
	public static final String KEYSTORE_PASSWORD = "keyStore_password";
	public static final String KEYSTORE_FILEPATH = 
			System.getProperty("user.dir") + File.separator + ".keystore";

	private final char[] keystorePassword;
	private final String keystoreFilePath;
	private final char[] keyPassword;

	private KeyStore keystore = null;
	
	public KeyStoreManager(final String keystoreFilepath
			, final String  keystorePassword, final String  keyPassword) throws Exception {
		
		this.keystorePassword = keystorePassword.toCharArray();
		this.keyPassword = keyPassword.toCharArray();
		
		final File file = new File(keystoreFilepath);
		if (file.exists()) {
			keystoreFilePath = file.getCanonicalPath();
			log.info("Key-store exists. File=[" + keystoreFilePath+"]");
			return;
		}
		
		log.info("Creating new empty key-store ...");
		if (!file.createNewFile()) {
			throw new IOException("Can't create new key-store file " + file);
		}

		keystoreFilePath = file.getCanonicalPath();

		final KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(null, this.keystorePassword);
		final KeyPair keyPair = generateKeyPairUsingKeystore(keystore);
		final Certificate[] certificateChain = new Certificate[1];  
		certificateChain[0] = generateCertificate(keyPair);  
		keystore.setKeyEntry(KEY_NAME, keyPair.getPrivate(), this.keyPassword, certificateChain);
		
		// done. Store away the key-store
		save(keystore);
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

	public void save() throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException {
		final FileOutputStream fos = new FileOutputStream(keystoreFilePath);
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
		final FileOutputStream fos = new FileOutputStream(keystoreFilePath);
		keystore.store(fos, keystorePassword);
		fos.close();
	}

	public void loadKeystore() throws Exception {
		if (null != keystore) {
			log.info("Existing key-store from [" + keystoreFilePath+ "]: "
					+ "\n\t - type: " + keystore.getType()
					+ "\n\t - provider: " + keystore.getProvider().getInfo()
					);
			return;
		}
		log.info("Loading key-store from [" + keystoreFilePath+ "] ...");
		try {
			keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		} catch (KeyStoreException e) {
			throw new Exception(
					"Can't get instance of the default key-store: ", e);
		}

		final FileInputStream fis;
		try {
			fis = new FileInputStream(keystoreFilePath);
		} catch (Exception e) {
			throw new Exception("Cannnot get input stream from file "
					+ keystoreFilePath, e);
		}
		try {
			keystore.load(fis, keystorePassword);
		} catch (Exception e) {
			throw new Exception("Failure loading key-store from file "
					+ keystoreFilePath, e);
		}
		log.info(sb("Key-store loaded from [" + keystoreFilePath+"]", keystore).toString());
		log.info(sb("Provider from the key-store", keystore.getProvider()).toString());
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
	public void lookup(final String keyName, final String keyPassword)
			throws Exception {
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
				return;
			}
			log.info("Key=[" + keyName + "] has private key ["+ key+"] Algorithm: " + key.getAlgorithm());
			final Certificate certs[] = keystore.getCertificateChain(keyName);
			log.info("Key=[" + keyName + "] has certificate-chain with size "+ certs.length);
			if (certs[0] instanceof X509Certificate) {
				X509Certificate x509 = (X509Certificate) certs[0];
				log.info("Key=[" + keyName + "] is really "+ x509.getSubjectDN());
			}
			if (certs[certs.length - 1] instanceof X509Certificate) {
				X509Certificate x509 = (X509Certificate) certs[certs.length - 1];
				log.info("Key=[" + keyName + "] was verified by " + x509.getIssuerDN());
			}
		} 
		else if (keystore.isCertificateEntry(keyName)) {
			log.info("Key=[" + keyName + "] is a certificate-entry in the keystore");
			final Certificate c = keystore.getCertificate(keyName);
			if (c instanceof X509Certificate) {
				final X509Certificate x509 = (X509Certificate) c;
				log.info(keyName + " is really " + x509.getSubjectDN());
				log.info(keyName + " was verified by " + x509.getIssuerDN());
			}
		} 
		else {
			log.warn("Key=[" + keyName + "] is unknown to this keystore");
		}
	}

	public static final void main(final String[] args) throws Exception {
		final KeyStoreManager keyStoreManager = new KeyStoreManager();
		keyStoreManager.loadKeystore();
		keyStoreManager.lookup(KEY_NAME, KEY_PASSWORD);
	}

}
