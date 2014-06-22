package de.dbo.samples.maven.basic.enterprise.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="Wind")
public class Wind extends AbstractBaseEntity {
	private static final long serialVersionUID = -6590430126609581934L;
	
	private String chill;
	private String direction;
	private String speed;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "weather_id", nullable = false)
	private Weather weather;

	public Wind() {
	}

	public String getChill() {
		return chill;
	}

	public void setChill(String newChill) {
		this.chill = newChill;
	}

	public final String getDirection() {
		return direction;
	}

	public final void setDirection(final String newDirection) {
		this.direction = newDirection;
	}

	public final String getSpeed() {
		return speed;
	}

	public final void setSpeed(final String newSpeed) {
		this.speed = newSpeed;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
	public final String toString() {
		final StringBuilder sb = new StringBuilder("Wind:");
		sb.append(" chill=" + chill);
		sb.append(" direction=" + direction);
		sb.append(" speed=" + speed);
		sb.append(super.toString());
		return sb.toString();
	}
	
}