package de.dbo.samples.crypto0.keystore;


import static de.dbo.samples.crypto0.keystore.KeyStoreConfig.*;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Random;

import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.x509.X509V3CertificateGenerator;

@SuppressWarnings("deprecation")
final class X509CertificateGenerator {
	
	private static final long SEC = 1000;
	private static final long MIN = SEC* 60;
	private static final long HOUR = MIN* 60;
	private static final long DAY = HOUR * 24;
	
	private static final String PRINCIPAL_LOCALHOST = "CN=localhost";
	private static final BigInteger SERIAL_NUMBER_FORMAT = new BigInteger("100000000000000000");
	
	/**
	 * Self-signed certificate for localhost
	 * 
	 * @param keyPair
	 * @return certificate (to be used while saving the key-pair in the key-store)
	 * @throws CertificateEncodingException
	 * @throws InvalidKeyException
	 * @throws IllegalStateException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 */
	static final X509Certificate generateCertificate(final KeyPair keyPair) 
			throws CertificateEncodingException, InvalidKeyException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException{  
		  final PublicKey publicKey = keyPair.getPublic();
		  final PrivateKey signingKey = keyPair.getPrivate();    
		   X509V3CertificateGenerator cert = new X509V3CertificateGenerator();   
		   cert.setSerialNumber(getRandomBigInteger());  
		   cert.setSubjectDN(new X509Principal(PRINCIPAL_LOCALHOST));  //see examples to add O,OU etc  
		   cert.setIssuerDN(new X509Principal(PRINCIPAL_LOCALHOST));  //same since it is self-signed  
		   cert.setNotBefore(new Date(System.currentTimeMillis()- MIN));  
		   cert.setNotAfter(new Date(System.currentTimeMillis() + 1000* DAY));  
		   cert.setSignatureAlgorithm(CERTIFICATE_SIGNATURE_ALGORITHM);  
		   
		   cert.setPublicKey(publicKey);  
		   return cert.generate(signingKey, KEYSTORE_PROVIDER_NAME);  
		}
	
	private static BigInteger getRandomBigInteger() {
		 	final Random rand = new Random();
	        final BigInteger upperLimit = SERIAL_NUMBER_FORMAT;
	        BigInteger result;
	        do {
	            result = new BigInteger(upperLimit.bitLength(), rand); 
	        } while(result.compareTo(upperLimit) >= 0);   
	        return result;
	 }
}
