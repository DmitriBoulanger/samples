package de.dbo.samples.spring.environments.annotation;

import static de.dbo.tools.utils.print.Print.line;

import java.util.Date;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public final class Print {
    
    public static StringBuilder print(final AnnotationConfigApplicationContext ctx) {
  	if (null==ctx) {
  	    return new StringBuilder("AnnotationConfigApplicationContext is null!");
  	}
  	final StringBuilder sb = new StringBuilder("AnnotationConfigApplicationContext:");
  	sb.append("\n\t - object:                     " + ctx.toString());
  	sb.append("\n\t - class:                      " + ctx.getClass().getName());
  	sb.append("\n\t - application name:           " + ctx.getApplicationName());
  	sb.append("\n\t - application display name:   " + ctx.getDisplayName());
  	sb.append("\n\t - application startup-date:   " + new Date(ctx.getStartupDate()));
  	return sb;
    }
 
}
