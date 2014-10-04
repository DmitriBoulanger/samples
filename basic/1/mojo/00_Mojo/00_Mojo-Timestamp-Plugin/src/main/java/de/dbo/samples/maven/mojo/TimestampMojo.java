package de.dbo.samples.maven.mojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;

/**
 * Print out time-stamp and elapsed ms.
 * @goal timestamp
 */
//see: http://books.sonatype.com/mvnref-book/reference/writing-plugins-sect-custom-plugin.html#writing-plugins-sect-class-annotations

public final class TimestampMojo extends AbstractMojo {
	
   /** @parameter */ String prefix = "Timestamp:";
   /** @parameter */ String datetimePattern = "yyyy-MM-dd HH:mm:ss.SSS";
   
   private static final String CTX_TIME_KEY = "TimestampMojo-Time";


@Override
public final void execute() {
      @SuppressWarnings("unchecked")
	  final Map<String,Object> ctx = getPluginContext();
      final Date  date = new Date();
      final Long  lasttime = (Long) ctx.get( CTX_TIME_KEY );
      ctx.put( CTX_TIME_KEY, new Long( date.getTime() ) );
      final String elapsed;
      if (lasttime == null) {
    	  elapsed = "";
      } else {
    	  elapsed = " Elapsed: " + (date.getTime() - lasttime.longValue()) + " ms.";
      }
      getLog().info( prefix + " " + (new SimpleDateFormat( datetimePattern ).format( date )) + elapsed );
   }

}