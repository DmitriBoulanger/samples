package de.dbo.samples.maven.mojo;

import static de.dbo.tools.utils.print.Print.lines;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadPropertiesMojoTest extends AbstractMojoTestCase {
	private static final Logger log = LoggerFactory.getLogger(ReadPropertiesMojoTest.class);

	private static final String TEST_ARTIFACT_ID = "de.dbo.samples.basic.1.mojo.test:01_Mojo-Properties-Test:jar:0.0.0-SNAPSHOT";
	private static final String TEST_MAVEN_PLUGIN = "01_Mojo-Properties";
			
	public void testPropertiesMojo() throws Exception {

		final String pom = getBasedir() + "/src/test/resources/test-pom.xml";
		log.info("POM = " + pom);
		final File pomFile = new File(pom);
		assertTrue("Pom-file  doesn't exist" + pom, pomFile.exists());
		assertTrue("Pom-file is not readable" + pom, pomFile.canRead());
		final MavenProject mavenProject = newMavenProject(pomFile);
		log.info(print(mavenProject).toString());
		assertEquals(TEST_ARTIFACT_ID, mavenProject.getId());
		
		final Map<String, Object> pluginContext = new HashMap<>();
		pluginContext.put("test_maven_project", mavenProject);
		executeMojo(pluginContext, pomFile);
		
		final Properties properties = mavenProject.getProperties();
		assertTrue(!properties.isEmpty());
		log.info("Project-properties: " + lines(properties,"-version"));
		assertEquals("1.0-resource",properties.getProperty("spring-version", null));
		assertEquals("4.0.0-resource",properties.getProperty("mysql-version", null));
	}

	private void executeMojo(final Map<String, ?> pluginContext, final File testPomFile) throws Exception {
		final ReadPropertiesMojo mojo = new ReadPropertiesMojo();
		configureMojo(mojo, TEST_MAVEN_PLUGIN, testPomFile);
		mojo.setPluginContext(pluginContext);
		mojo.setLog( new SystemStreamLog() {
			@Override
			public void info(final CharSequence content) {
				log.info(content.toString());
			}
		});
		mojo.execute();
	}
	
	private MavenProject newMavenProject(final File pomFile) throws Exception{
		if (null==pomFile) {
			log.warn("POM for maven-project is null!");
			return null;
		}
		final Model model;
		FileReader reader = null;
		final MavenXpp3Reader mavenreader = new MavenXpp3Reader();
		try {
			reader = new FileReader(pomFile);
			model = mavenreader.read(reader);
		} catch (Exception e) {
			log.error("Can't create model from POM " + pomFile.getAbsolutePath(), e);
			throw e;
		}  finally {
			if (null!=reader) {
				try {
					reader.close();
				} catch (Exception e) {
					log.error("Can't close reader from POM "+ pomFile.getAbsolutePath(),e);
				}
			}
		}
		return new MavenProject(model);
	}
	

	private static StringBuilder print(final MavenProject mavenProject) {
		final StringBuilder sb = new StringBuilder("Maven-project:");
		sb.append("\n\t - Id           : " + mavenProject.getId());
		sb.append("\n\t - Version      : " + mavenProject.getVersion());
		sb.append("\n\t - Parent       : " + mavenProject.getParent());
		sb.append("\n\t - Artifact     : " + mavenProject.getArtifact());
		sb.append("\n\t - Dependencies : " + print(mavenProject.getDependencies()));
        return sb;
	}
	
	private static final StringBuilder print(final List<MavenProject> dependencies) {
		final StringBuilder sb = new StringBuilder();
		if (null==dependencies) {
			sb.append("NULL");
		}
		else if (dependencies.isEmpty()) {
			sb.append("[]");
		} else {
			for (final MavenProject mavenProject:dependencies) {
				sb.append("\n\t\t -- " + mavenProject.getArtifact());
			}
		}
		
		
		return sb;
	}
}
