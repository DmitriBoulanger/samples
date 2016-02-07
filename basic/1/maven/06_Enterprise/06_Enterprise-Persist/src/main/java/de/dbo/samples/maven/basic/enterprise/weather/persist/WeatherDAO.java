package de.dbo.samples.maven.basic.enterprise.weather.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.dbo.samples.maven.basic.enterprise.weather.model.Location;
import de.dbo.samples.maven.basic.enterprise.weather.model.Weather;

public final class WeatherDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(WeatherDAO.class);

    public WeatherDAO() {}

    public void save(final Weather weather) {
	final Object id = getHibernateTemplate().save( weather );
	log.info("weather saved ID=" + id);
    }

    public Weather load(String id) {
	final Weather weather = (Weather) getHibernateTemplate().load( Weather.class, id);
	log.info("weather loaded ID=" + id + " ==> " + weather);
	return weather;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Weather> recentForLocation(final Location location) {
	final List<Weather> ret = (List<Weather>) getHibernateTemplate().execute(
		new HibernateCallback() {
		    public Object doInHibernate(Session session) {
			final Query query = getSession().getNamedQuery("Weather.byLocation");
			query.setParameter("location", location);
			return new ArrayList<Weather>(query.list());
		    }
		});
	log.info("retrieved " +ret.size() + " weather reports for " + location);
	return ret;
    }
}