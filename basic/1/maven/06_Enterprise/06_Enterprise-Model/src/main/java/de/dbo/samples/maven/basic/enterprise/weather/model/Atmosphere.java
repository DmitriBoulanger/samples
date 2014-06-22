package de.dbo.samples.maven.basic.enterprise.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="Atmosphere")
public class Atmosphere extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	private String humidity;
	private String visibility;
	private String pressure;
	private String rising;
	
	public Atmosphere()  {
		
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "weather_id", nullable = false)
	private Weather weather;

	public final String getHumidity() {
		return humidity;
	}

	public final void setHumidity(final String newHumidity) {
		this.humidity = newHumidity;
	}

	public final String getVisibility() {
		return visibility;
	}

	public final void setVisibility(final String newVisibility) {
		this.visibility = newVisibility;
	}

	public final String getPressure() {
		return pressure;
	}

	public final void setPressure(final String newPressure) {
		this.pressure = newPressure;
	}

	public final String getRising() {
		return rising;
	}

	public final void setRising(final String newRising) {
		this.rising = newRising;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
	public final String toString() {
		final StringBuilder sb = new StringBuilder("Atmosphere:");
		sb.append(" humidity=" + humidity);
		sb.append(" visibility=" + visibility);
		sb.append(" pressure=" + pressure);
		sb.append(" rising=" + rising);
		sb.append(super.toString());
		return sb.toString();
	}

}