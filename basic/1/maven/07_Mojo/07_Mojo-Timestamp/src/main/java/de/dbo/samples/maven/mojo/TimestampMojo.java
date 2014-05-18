package de.dbo.samples.maven.mojo;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.maven.plugin.AbstractMojo;

/**
 * Print out time-stamp and elapsed ms.
 * @goal timestamp
 */
//see: http://books.sonatype.com/mvnref-book/reference/writing-plugins-sect-custom-plugin.html#writing-plugins-sect-class-annotations
public final class TimestampMojo extends AbstractMojo
{
	
   /** @parameter */ String prefix = "Timestamp:";
   /** @parameter */ String datetimePattern = "yyyy-MM-dd HH:mm:ss,S";

 @SuppressWarnings("unchecked")
 @Override
public final void execute() {
      final String CTX_TIME_KEY = "TimestampMojo-Time";
      final Date  date = new Date();
      final Long  lasttime = (Long) getPluginContext().get( CTX_TIME_KEY );
      getPluginContext().put( CTX_TIME_KEY, new Long( date.getTime() ) );
      final String elapsed = ( lasttime == null ) ? "" :
            " Elapsed: " + (date.getTime() - lasttime.longValue()) + " ms.";
      getLog().info( prefix + " " +
            (new SimpleDateFormat( datetimePattern ).format( date )) + elapsed );
   }

}