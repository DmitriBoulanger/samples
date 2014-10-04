package de.dbo.samples.maven.mojo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimestampMojoTest extends AbstractMojoTestCase {
	private static final Logger log = LoggerFactory.getLogger(TimestampMojoTest.class);
	
	public void testTimestampMojo() throws Exception {
		final Map<?, ?> pluginContext = new HashMap<>();
		String log1 = executeMojo(pluginContext, "executeMojo1");
		log.info("log1: " + log1);
		String log2 = executeMojo(pluginContext, "executeMojo2");
		log.info("log2: " + log2);
		
		assertTrue(log1.length() < log2.length());
		assertTrue(!log1.contains("Elapsed"));
		assertTrue(log2.contains("Elapsed"));
	}

	private String executeMojo(final Map<?, ?> pluginContext, final String comment) throws Exception {
		final String testPom = getBasedir() + "/src/test/resources/test-pom.xml";
		log.info(comment + " testPom=" + testPom);
		final File testPomFile = new File(testPom);
		assertTrue("Can't read file "+testPom, testPomFile.canRead());
		final String artifactId = "00_Mojo-Timestamp";
		final StringBuffer testlog = new StringBuffer();
		final TimestampMojo mojo = new TimestampMojo();
		configureMojo(mojo, artifactId, new File(testPom));
		mojo.setPluginContext(pluginContext);
		mojo.setLog(new TestLog(testlog));
		mojo.execute();
		log.info(comment + " testlog: " + testlog);
		final String prefix = (String) getVariableValueFromObject(mojo,"prefix");
		log.info(comment + " prefix: " + prefix);
		assertNotNull(prefix);
		assertEquals(prefix, testlog.substring(0, prefix.length()));
		return testlog.toString();
	}

	final class TestLog extends SystemStreamLog {
		
		final StringBuffer log;

		public TestLog(final StringBuffer log) {
			this.log = log;
		}

		@Override
		public void info(final CharSequence content) {
			log.append(content);
		}
	}
}
