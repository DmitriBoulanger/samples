package de.dbo.samples.spring.environments.env.prod;

import de.dbo.samples.spring.environments.env.GenericEnv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ashraf
 *
 */
@Component
public class ProdEnv implements GenericEnv {

	private String envName = "prod";

	@Value("${profile.name}")
	private String profileName;

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	@Override
	public String toString() {
		return "ProdEnv [envName=" + envName + ", profileName=" + profileName
				+ "]";
	}

}
