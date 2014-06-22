package de.dbo.samples.maven.basic.enterprise.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="WeatherCondition")
public class Condition extends AbstractBaseEntity {
	private static final long serialVersionUID = 1919947344174594297L;
	
	private String text;
	private String code;
	private String temp;
	private String date;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "weather_id", nullable = false)
	private Weather weather;

	public Condition() {

	}

	public String getText() {
		return text;
	}

	public void setText(String newText) {
		this.text = newText;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String newCode) {
		this.code = newCode;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String newTemp) {
		this.temp = newTemp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String newDate) {
		this.date = newDate;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
	public final String toString() {
		final StringBuilder sb = new StringBuilder("Condition:");
		sb.append(" text=" + text);
		sb.append(" code=" + code);
		sb.append(" temp=" + temp);
		sb.append(" date=" + date);
		sb.append(super.toString());
		return sb.toString();
	}

}