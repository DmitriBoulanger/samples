package de.dbo.samples.spring.environments.annotation;

import static de.dbo.tools.utils.print.Print.line;

import java.util.Date;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public final class Tool {
    
    public static StringBuilder print(final AnnotationConfigApplicationContext ctx) {
  	if (null==ctx) {
  	    System.err.println("AnnotationConfigApplicationContext is null!");
  	    return new StringBuilder();
  	}
  	final StringBuilder sb = new StringBuilder("\nAnnotationConfigApplicationContext:");
  	sb.append("\n\t - object:                     " + ctx.toString());
  	sb.append("\n\t - class:                      " + ctx.getClass().getName());
  	sb.append("\n\t - application name:           " + ctx.getApplicationName());
  	sb.append("\n\t - application display name:   " + ctx.getDisplayName());
  	sb.append("\n\t - application startup-date:   " + new Date(ctx.getStartupDate()));
  	return sb;
    }
    
    public static StringBuilder print(final Environment env) {
  	if (null==env) {
  	    return new StringBuilder("Environment is null!");
  	}
  	final StringBuilder sb = new StringBuilder("\nEnvironment:");
  	sb.append("\n\t - object:            " + env.toString());
  	sb.append("\n\t - class:             " + env.getClass().getName());
  	sb.append("\n\t - profiles default:  " + line(env.getDefaultProfiles()));
  	sb.append("\n\t - profiles active:   " + line(env.getActiveProfiles()));
  	return sb;
    }
    
    

}
