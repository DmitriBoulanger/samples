package de.dbo.samples.maven.basic.enterprise.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="WeatherCondition")
public class Condition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String text;
	private String code;
	private String temp;
	private String date;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "weather_id", nullable = false)
	private Weather weather;

	public Condition() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		sb.append(" id=" + id);
		sb.append(" text=" + text);
		sb.append(" code=" + code);
		sb.append(" temp=" + temp);
		sb.append(" date=" + date);
		return sb.toString();
	}

}