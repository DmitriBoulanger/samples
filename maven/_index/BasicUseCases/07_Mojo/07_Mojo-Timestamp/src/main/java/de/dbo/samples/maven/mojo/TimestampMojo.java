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

   public void execute()
   {
      final String CTX_TIME_KEY = "TimestampMojo-Time";
      Date  date = new Date();
      Map   ctx = getPluginContext();
      Long  timeZuletzt = (Long) ctx.get( CTX_TIME_KEY );
      ctx.put( CTX_TIME_KEY, new Long( date.getTime() ) );
      String dauer = ( timeZuletzt == null ) ? "" :
            ", Dauer: " + (date.getTime() - timeZuletzt.longValue()) + " ms";
      getLog().info( prefix + " " +
            (new SimpleDateFormat( datetimePattern ).format( date )) + dauer );
   }
}