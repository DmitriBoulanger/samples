package de.dbo.samples.spring.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service implementation used at the server-side
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class ServiceImpl implements Service{
	private static final Logger log = LoggerFactory.getLogger(ServiceImpl.class);
	
	public ServiceImpl() {
	    log.info("" + this.hashCode());
	}

	@Override
	public int cube(int number) {
		log.info("cube for " + number + " ...");
		return number*number*number;
	}
	
	@Override
	public String ping() {
	    return "" + this.hashCode();
	}

}
