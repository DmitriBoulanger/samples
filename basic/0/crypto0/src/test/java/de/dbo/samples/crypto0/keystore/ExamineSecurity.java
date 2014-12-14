package de.dbo.samples.crypto0.keystore;

import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamineSecurity {
	private static final Logger log = LoggerFactory.getLogger(ExamineSecurity.class);
	
	public static void main(String args[]) {
			final Provider p[] = Security.getProviders();
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < p.length; i++) {
				sb.append("\n\tProvider: " + p[i]);
				for (final Enumeration<?> e = p[i].keys(); e.hasMoreElements();)
					sb.append("\n\t\t - " + e.nextElement());
			}
			log.info("Security examination: " + sb);
		}
}
