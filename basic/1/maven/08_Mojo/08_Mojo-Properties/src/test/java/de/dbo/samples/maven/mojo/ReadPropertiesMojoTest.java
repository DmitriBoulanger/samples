package de.dbo.samples.maven.mojo;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadPropertiesMojoTest extends AbstractMojoTestCase {
	private static final Logger log = LoggerFactory.getLogger(ReadPropertiesMojoTest.class);

	public void testPropertiesMojo() throws Exception {
		final Map<Object, Object> pluginContext = new HashMap<>();
		final String pom = getBasedir() + "/src/test/resources/test-pom.xml";
		log.info("POM = " + pom);
		final File pomFile = new File(pom);
		assertTrue("Can't read pom-file " + pom, pomFile.canRead());

		Model model = null;
		FileReader reader = null;
		MavenXpp3Reader mavenreader = new MavenXpp3Reader();
		try {
			reader = new FileReader(pomFile);
			model = mavenreader.read(reader);
		} catch (Exception e) {
			log.error("Can't create model from POM "+pom,e);
		}
		pluginContext.put("test_maven_project", new MavenProject(model));
		executeMojo(pluginContext, pomFile);
	}

	private void executeMojo(final Map<?, ?> pluginContext, File testPomFile) throws Exception {
		
		final TestLog testLog = new TestLog();

		final ReadPropertiesMojo mojo = new ReadPropertiesMojo();
		configureMojo(mojo, "08_Mojo-Properties", testPomFile);
		mojo.setPluginContext(pluginContext);
		mojo.setLog(testLog);
		mojo.execute();
	}

	final class TestLog extends SystemStreamLog {
		
		
		@Override
		public void info(final CharSequence content) {
			log.info(content.toString());
		}
	}
}
