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
				return (Location) query.uniqueResult();
			}
		});
    	log.info("retrieved zip="+zip + " ==> " + ret);
    	return ret;
    }
    
	public List<Location> all() {
    	return new ArrayList<Location>( getHibernateTemplate().loadAll(Location.class) );
    }

}