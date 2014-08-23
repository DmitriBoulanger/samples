package de.dbo.samples.threadlocal0.dateformat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Using synchronization means a single instance of the unsafe class 
 * may be used, but sacrifices "liveness" because only 
 * one thread can use the object at any one time. 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

public class SynchronizedDateParser implements DateParser {
	
  private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  
  public synchronized Date parse(String text) throws ParseException {
    return dateFormat.parse(text);
  }
}
