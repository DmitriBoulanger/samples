package de.dbo.samples.maven.mojo;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class TimestampMojoTest extends AbstractMojoTestCase
{
   public void testTimestampMojo() throws Exception
   {
      final Map<?,?> pluginContext = new HashMap<>();
      String log1 = executeMojo( pluginContext );
      String log2 = executeMojo( pluginContext );
      assertTrue(  log1.length() < log2.length() );
      assertTrue( !log1.contains( "Dauer" ) );
      assertTrue(  log2.contains( "Dauer" ) );
   }

   private String executeMojo( final Map<?,?> pluginContext ) throws Exception
   {
      String        testPom = getBasedir() + "/src/test/resources/test-pom.xml";
      String        artifactId = "00_Timestamp";
      StringBuffer  log  = new StringBuffer();
      TimestampMojo mojo = new TimestampMojo();
      configureMojo( mojo, artifactId, new File( testPom ) );
      mojo.setPluginContext( pluginContext );
      mojo.setLog( new TestLog( log ) );
      mojo.execute();
      String prefix = (String) getVariableValueFromObject( mojo, "prefix" );
      assertNotNull( prefix );
      assertEquals( prefix, log.substring( 0, prefix.length() ) );
      return log.toString();
   }

   class TestLog extends SystemStreamLog
   {
      StringBuffer log;

      public TestLog( StringBuffer log )
      {
         this.log = log;
      }

      @Override
      public void info( CharSequence content )
      {
         log.append( content );
      }
   }
}
