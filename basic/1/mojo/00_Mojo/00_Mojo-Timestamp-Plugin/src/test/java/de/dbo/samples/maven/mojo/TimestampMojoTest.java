package de.dbo.samples.maven.mojo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class TimestampMojoTest extends AbstractMojoTestCase {
	private static final Logger log = LoggerFactory.getLogger(TimestampMojoTest.class);
	
	private static final String PLUGIN_ARTIFACT =  "00_Mojo-Timestamp-Plugin";
	private static final String TEST_POM_PATH =  getBasedir() + "/src/test/resources/test-pom.xml";
	
	private final Map<?, ?> pluginContext = new HashMap<>();
	
	public TimestampMojoTest() {
		log.info("Test POM path =" + TEST_POM_PATH);
	}
	
	public void testTimestampMojo() throws Exception {
		final String log1 = executeMojo("execute Mojo 1");
		final String log2 = executeMojo("execute Mojo 2");
		
		/* test the contents of the log-accumulators */
		log.info("log1: " + log1);
		log.info("log2: " + log2);
		assertTrue(log1.length() < log2.length());
		assertTrue(!log1.contains("Elapsed"));
		assertTrue(log2.contains("Elapsed"));
	}

	private final String executeMojo(final String comment) throws Exception {
		final StringBuffer testlogBuffer = new StringBuffer();
		final Log testLog = new TimestampMojoLog(testlogBuffer);
		final TimestampMojo mojo = new TimestampMojo();
		configureMojo(mojo, PLUGIN_ARTIFACT, testPomFile(comment));
		mojo.setPluginContext(pluginContext);
		mojo.setLog(testLog);
		mojo.execute();
		
		log.debug(comment + ": " + testlogBuffer);
		final String prefix = (String) getVariableValueFromObject(mojo,"prefix");
		log.debug(comment + ": " + prefix);
		assertNotNull(prefix);
		assertEquals(prefix, testlogBuffer.substring(0, prefix.length()));
		return testlogBuffer.toString();
	}
	
	private static final File testPomFile(final String comment) {
		final File testPomFile = new File(TEST_POM_PATH);
		assertTrue("Test-file " + TEST_POM_PATH + " doesn't exist", testPomFile.exists());
		assertTrue("Test-file " + TEST_POM_PATH + " is unreadble", testPomFile.canRead());
		return testPomFile;
	}
}
