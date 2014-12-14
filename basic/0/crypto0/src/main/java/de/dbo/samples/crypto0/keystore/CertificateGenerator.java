package de.dbo.samples.crypto0.keystore;

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

import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.x509.X509V3CertificateGenerator;

@SuppressWarnings("deprecation")
final class CertificateGenerator {

	static final X509Certificate generateCertificate(KeyPair keyPair) throws CertificateEncodingException, InvalidKeyException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException{  
		  final PublicKey publicKey = keyPair.getPublic();
		  final PrivateKey signingKey = keyPair.getPrivate();    
		   X509V3CertificateGenerator cert = new X509V3CertificateGenerator();   
		   cert.setSerialNumber(BigInteger.valueOf(1));   //or generate a random number  
		   cert.setSubjectDN(new X509Principal("CN=localhost"));  //see examples to add O,OU etc  
		   cert.setIssuerDN(new X509Principal("CN=localhost")); //same since it is self-signed  
		   cert.setNotBefore(new Date(System.currentTimeMillis()-1000));  
		   cert.setNotAfter(new Date(System.currentTimeMillis() + 100000000));  
		   cert.setSignatureAlgorithm("SHA1withDSA");  
		   
		   cert.setPublicKey(publicKey);  
		   return cert.generate(signingKey, "SUN");  
		}
}
