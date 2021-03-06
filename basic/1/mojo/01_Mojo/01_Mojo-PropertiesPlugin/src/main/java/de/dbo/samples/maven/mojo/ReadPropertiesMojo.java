package de.dbo.samples.maven.mojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.io.location.ClasspathResourceLocatorStrategy;
import org.apache.maven.shared.io.location.FileLocatorStrategy;
import org.apache.maven.shared.io.location.Location;
import org.apache.maven.shared.io.location.Locator;
import org.apache.maven.shared.io.location.LocatorStrategy;
import org.apache.maven.shared.io.location.URLLocatorStrategy;
import org.codehaus.plexus.util.cli.CommandLineUtils;

/*
 * The read-project-properties goal reads property files and stores the
 * properties as project properties. It serves as an alternate to specifying properties in pom.xml. 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */


//@Mojo( name = "Maven Properties Plug-in" )
/**
 * @goal read-project-properties
 */
public final class ReadPropertiesMojo extends AbstractMojo {

//	@Parameter(required=true,readonly=true)
//	private MavenProject project;
	
	/**
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;
	

	/**
	 * The properties files that will be used when reading properties. RS: made
	 * optional to avoid issue for inherited plug-ins
	 * @parameter
	 */
	private File[] files;

	// Begin: RS addition
	/**
	 * Optional paths to properties files to be used.
	 * @parameter
	 */
	private String[] filePaths;
	// End: RS addition

	public void execute() throws MojoExecutionException {
		
		//TODO the test-hook. Should be removed. How?
		if (getPluginContext().containsKey("test_maven_project")) {
			project = (MavenProject) getPluginContext().get("test_maven_project");
			logProject();
		}

		// Begin: RS addition
		readPropertyFiles();
		// End: RS addition

		Properties projectProperties = new Properties();
		for (int i = 0; i < files.length; i++) {
			final File file = files[i];
			if (null==file) {
				throw new MojoExecutionException("File is NULL!");
			}
			if (!file.exists()) {
				getLog().warn("Ignoring missing properties file: "+ file.getAbsolutePath());
				continue;
			}

			try {
				getLog().info("Loading property file: " + file);
				FileInputStream stream = new FileInputStream(file);
				projectProperties = project.getProperties();
				try {
					projectProperties.load(stream);
				} finally {
					if (stream != null) {
						stream.close();
					}
				}
			} catch (IOException e) {
				throw new MojoExecutionException("Error reading properties file " + file.getAbsolutePath(), e);
			}
		}

		boolean useEnvVariables = false;
		for (@SuppressWarnings("rawtypes")
		Enumeration n = projectProperties.propertyNames(); n.hasMoreElements();) {
			String k = (String) n.nextElement();
			String p = (String) projectProperties.get(k);
			if (p.indexOf("${env.") != -1) {
				useEnvVariables = true;
				break;
			}
		}
		Properties environment = null;
		if (useEnvVariables) {
			try {
				environment = CommandLineUtils.getSystemEnvVars();
			} catch (IOException e) {
				throw new MojoExecutionException("Error getting system envorinment variables: ", e);
			}
		}
		for (@SuppressWarnings("rawtypes")
		Enumeration n = projectProperties.propertyNames(); n
				.hasMoreElements();) {
			String k = (String) n.nextElement();
			projectProperties.setProperty(k,
					getPropertyValue(k, projectProperties, environment));
		}
	}

	// Begin: RS addition
	/**
	 * Obtain the file from the local project or the classpath
	 * 
	 * @throws MojoExecutionException
	 */
	private void readPropertyFiles() throws MojoExecutionException {
		if (files == null || files.length == 0) {
			throw new MojoExecutionException("no files or filePaths defined, one or both must be specified");
		}

		File[] allFiles;

		int offset = 0;
		if (files != null && files.length != 0) {
			allFiles = new File[files.length + filePaths.length];
			System.arraycopy(files, 0, allFiles, 0, files.length);
			offset = files.length;
		} else {
			allFiles = new File[filePaths.length];
		}

		for (int i = 0; i < filePaths.length; i++) {
			Location location = getLocation(filePaths[i], project);
			if (null == location) {
				throw new MojoExecutionException(
						"Location is null: filePath=["+ filePaths[i]+ "]"
								+ "project=["+ (null != project ? project.getId(): "NULL") + "]");
			}

			try {
				allFiles[offset + i] = location.getFile();
			} catch (IOException e) {
				throw new MojoExecutionException("unable to open properties file", e);
			}
		}

		// replace the original array with the merged results
		files = allFiles;
		logPropertyFiles();
	}

	// End: RS addition

	/**
	 * Retrieves a property value, replacing values like ${token} using the
	 * Properties to look them up. Shamelessly adapted from:
	 * http://maven.apache.
	 * org/plugins/maven-war-plugin/xref/org/apache/maven/plugin
	 * /war/PropertyUtils.html
	 * 
	 * It will leave unresolved properties alone, trying for System properties,
	 * and environment variables and implements reparsing (in the case that the
	 * value of a property contains a key), and will not loop endlessly on a
	 * pair like test = ${test}
	 * 
	 * @param k
	 *            property key
	 * @param p
	 *            project properties
	 * @param environment
	 *            environment variables
	 * @return resolved property value
	 */
	private String getPropertyValue(String k, Properties p, Properties environment) {
		String v = p.getProperty(k);
		String ret = "";
		int idx, idx2;

		while ((idx = v.indexOf("${")) >= 0) {
			// append prefix to result
			ret += v.substring(0, idx);

			// strip prefix from original
			v = v.substring(idx + 2);

			idx2 = v.indexOf("}");

			// if no matching } then bail
			if (idx2 < 0) {
				break;
			}

			// strip out the key and resolve it
			// resolve the key/value for the ${statement}
			String nk = v.substring(0, idx2);
			v = v.substring(idx2 + 1);
			String nv = p.getProperty(nk);

			// try global environment
			if (nv == null) {
				nv = System.getProperty(nk);
			}

			// try environment variable
			if (nv == null && nk.startsWith("env.") && environment != null) {
				nv = environment.getProperty(nk.substring(4));
			}

			// if the key cannot be resolved,
			// leave it alone ( and don't parse again )
			// else prefix the original string with the
			// resolved property ( so it can be parsed further )
			// taking recursion into account.
			if (nv == null || nv.equals(nk)) {
				ret += "${" + nk + "}";
			} else {
				v = nv + v;
			}
		}
		return ret + v;
	}

	// Begin: RS addition
	/**
	 * Use various strategies to discover the file.
	 */
	public Location getLocation(String path, MavenProject project) {
		final LocatorStrategy classpathStrategy = new ClasspathResourceLocatorStrategy();

		final List<LocatorStrategy> strategies = new ArrayList<LocatorStrategy>();
		strategies.add(classpathStrategy);
		strategies.add(new FileLocatorStrategy());
		strategies.add(new URLLocatorStrategy());

		final List<LocatorStrategy> refStrategies = new ArrayList<LocatorStrategy>();
		refStrategies.add(classpathStrategy);

		final Locator locator = new Locator();
		locator.setStrategies(strategies);

		final Location location = locator.resolve(path);
		try {
			getLog().info("location=" + location.getFile().toString());
		} catch (IOException e) {
			getLog().error(e);
		}
		return location;
	}

	// End: RS addition

	//
	// Debugging
	//

	private void logProject() {
		getLog().info("Maven project: [" + (null != project ? project.getArtifactId() : "NULL") + "]");
	}

	private final void logPropertyFiles() {
		final StringBuilder sb = new StringBuilder();
		if (null != files) {
			for (int i = 0; i < files.length; i++) {
				sb.append("\n\t - " + files[i].toString());
			}
		} else {
			sb.append("NULL");
		}
		getLog().info(files.length + " property-files done:" + sb.toString());
	}
}