package de.dbo.samples.crypto0.keystore;

import java.io.File;

public class KeyStoreConfig {
	
	public static final String KEY_NAME = "TestKey";
	public static final String KEY_PASSWORD = "key_pwd";
	public static final String KEYSTORE_PASSWORD = "keyStore_pwd";
	public static final String KEYSTORE_PROVIDER_NAME = "SUN";
	public static final String KEY_PAIR_GENERATOR_NAME = "KeyPairGenerator.DSA";
	
	public static final String CERTIFICATE_NAME = "TestCertificate";
	public static final String CERTIFICATE_SIGNATURE_ALGORITHM = "SHA1withDSA";
	
	public static final String KEYSTORE_FILEPATH = 
			System.getProperty("user.dir") + File.separator + ".keystore";
}
