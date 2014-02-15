package de.dbo.samples.maven.mojo;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.maven.plugin.AbstractMojo;

/**
 * @goal timestamp
 */
public class TimestampMojo extends AbstractMojo
{
   /** @parameter */ String prefix = "Timestamp:";
   /** @parameter */ String datetimePattern = "yyyy-MM-dd HH:mm:ss,S";

 @SuppressWarnings("unchecked")
public void execute() {
      final String CTX_TIME_KEY = "TimestampMojo-Time";
      final Date  date = new Date();
      final Long  timeZuletzt = (Long) getPluginContext().get( CTX_TIME_KEY );
      getPluginContext().put( CTX_TIME_KEY, new Long( date.getTime() ) );
      final String elapsed = ( timeZuletzt == null ) ? "" :
            ", Elapsed: " + (date.getTime() - timeZuletzt.longValue()) + " ms";
      getLog().info( prefix + " " +
            (new SimpleDateFormat( datetimePattern ).format( date )) + elapsed );
   }
}