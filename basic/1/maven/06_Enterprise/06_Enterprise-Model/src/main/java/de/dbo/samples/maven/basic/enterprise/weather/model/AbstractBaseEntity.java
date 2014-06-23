package de.dbo.samples.maven.basic.enterprise.weather.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

 
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Id
	protected final String id;
 
	public AbstractBaseEntity() {
		this.id = UUID.randomUUID().toString().replace("-", "");
	}
 
	@Override
	public int hashCode() {
		return id.hashCode();
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractBaseEntity)) {
			return false;
		}
		AbstractBaseEntity other = (AbstractBaseEntity) obj;
		return getId().equals(other.getId());
	}

	public String getId() {
		return id;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(" ID=" + id);
		return sb.toString();
	}
}

