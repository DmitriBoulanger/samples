package de.dbo.samples.maven.basic.enterprise.weather.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name="Location")
@NamedQueries({ @NamedQuery(name = "Location.uniqueByZip", query = "select l from Location l where l.zip = :zip") })
public class Location extends AbstractBaseEntity {
	private static final long serialVersionUID = -4171882636642905034L;
	
	private String zip;
	private String city;
	private String region;
	private String country;

	public Location() {
		
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public final String toString() {
		final StringBuilder sb = new StringBuilder("Location:");
		sb.append(" zip=" + zip);
		sb.append(" city=" + city);
		sb.append(" region=" + region);
		sb.append(" country=" + country);
		sb.append(super.toString());
		return sb.toString();
	}

}