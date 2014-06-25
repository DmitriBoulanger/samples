package de.dbo.samples.maven.basic.enterprise.weather.persist;

import de.dbo.samples.maven.basic.enterprise.weather.model.Location;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LocationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(LocationDAO.class);
	
    public LocationDAO() {}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Location findByZip(final String zip) {
    	final Location ret =  (Location) getHibernateTemplate().execute(
    		new HibernateCallback() {
    			public Object doInHibernate(Session session) {
    				Query query = getSession().getNamedQuery("Location.uniqueByZip");
    				query.setString("zip", zip);
				final List<Location> locations = (List<Location>)  query.list();
				if (null==locations) {
					throw new RuntimeException("Locations is null!");
				} 
				if (locations.isEmpty()) {
					log.warn("No locations found");
					return null;
				} 
				if (1!=locations.size()) {
					log.warn("Locationquery did not return a unique result ("+locations.size()+"). Using the first one ...");
				}
				return locations.get(0);
			}
		});
    	log.info("retrieved zip="+zip + " ==> " + ret);
    	return ret;
    }
    
	public List<Location> all() {
    	return new ArrayList<Location>( getHibernateTemplate().loadAll(Location.class) );
    }

}