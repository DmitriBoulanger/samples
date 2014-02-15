package de.dbo.samples.maven.mojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.maven.plugin.AbstractMojo;

/**
 * @goal timestamp
 */
public class TimestampMojo extends AbstractMojo
{
   /** @parameter */ String prefix = "Timestamp:";
   /** @parameter */ String datetimePattern = "yyyy-MM-dd HH:mm:ss,S";

   public void execute()
   {
      getLog().info( prefix + " " +
         (new SimpleDateFormat( datetimePattern ).format( new Date() )) );
   }
}
