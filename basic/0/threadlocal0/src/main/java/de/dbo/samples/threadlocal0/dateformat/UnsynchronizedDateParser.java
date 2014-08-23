package de.dbo.samples.threadlocal0.dateformat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * Segregated instances work by creating a new object for each client, 
 * which wastes more resources but does not sacrifice liveness. 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class UnsynchronizedDateParser implements DateParser {
	
  private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  
  public Date parse(String text) throws ParseException {
    return dateFormat.parse(text);
  }
}
