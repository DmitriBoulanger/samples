package de.dbo.samples.crypto0.keystore;

import static de.dbo.samples.crypto0.keystore.Utils.sb;

import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import java.util.Set;

class Utils
{
    private static String digits = "0123456789abcdef";
    
    /**
     * Return length many bytes of the passed in byte array as a hex string.
     * 
     * @param data the bytes to be converted.
     * @param length the number of bytes in the data block to be converted.
     * @return a hex representation of length bytes of data.
     */
    public static String toHex(byte[] data, int length)
    {
        StringBuffer  buf = new StringBuffer();
        
        for (int i = 0; i != length; i++)
        {
            int v = data[i] & 0xff;
            
            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }
        
        return buf.toString();
    }
    
    /**
     * Return the passed in byte array as a hex string.
     * 
     * @param data the bytes to be converted.
     * @return a hex representation of data.
     */
    public static String toHex(byte[] data)
    {
        return toHex(data, data.length);
    }
    

	static final StringBuilder sb(final Set<Object> set) {
		final StringBuilder sb = new StringBuilder();
		for (final Object o:set) {
			sb.append( "\n\t\t -  " + o);
		}
		return sb;
	}
	
	static final StringBuilder sb(final String title, KeyStore keystore) throws KeyStoreException {
		final StringBuilder sb = new StringBuilder(title+ ": ");
		sb.append(" type=" + keystore.getType());
		sb.append(" size=" + keystore.size());
		return sb;
	}
	
	static final StringBuilder sb(final String title, final KeyPair keyPair) throws KeyStoreException {
		final StringBuilder sb = new StringBuilder(title+ ": ");
		sb.append("\n\t public " + keyPair.getPublic().toString());
		sb.append("\n\t private " + keyPair.getPrivate().toString());
		return sb;
	}
	
	static final StringBuilder sb(final String title, Provider provider) {
		final StringBuilder sb = new StringBuilder(title+ ": ");
		sb.append("\n\t - name " + provider.getName());
		sb.append("\n\t - keys " + sb(provider.keySet()));
		sb.append("\n\t - size " + provider.size());
		sb.append("\n\t - info " + provider.getInfo());
		return sb;
	}
}
