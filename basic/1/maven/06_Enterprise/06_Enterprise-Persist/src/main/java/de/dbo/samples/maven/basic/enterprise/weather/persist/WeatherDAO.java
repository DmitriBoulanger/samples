package de.dbo.samples.maven.basic.enterprise.weather.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.dbo.samples.maven.basic.enterprise.weather.model.Location;
import de.dbo.samples.maven.basic.enterprise.weather.model.Weather;

public final class WeatherDAO extends HibernateDaoSupport {

    public WeatherDAO() {}

    public void save(Weather weather) {
    	getHibernateTemplate().save( weather );
    }

    public Weather load(String id) {
    	return (Weather) getHibernateTemplate().load( Weather.class, id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Weather> recentForLocation( final Location location ) {
    	return (List<Weather>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = getSession().getNamedQuery("Weather.byLocation");
				query.setParameter("location", location);
				return new ArrayList<Weather>( query.list() );
			}
		});
    }
}