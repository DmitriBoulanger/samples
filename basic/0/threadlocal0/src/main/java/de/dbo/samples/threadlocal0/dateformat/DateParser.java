package de.dbo.samples.threadlocal0.dateformat;

import java.text.ParseException;
import java.util.Date;

public interface DateParser {
	public Date parse(String text) throws ParseException;
}
 
