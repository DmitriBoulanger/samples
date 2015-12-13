package de.dbo.samples.spring.environments.annotation.cfgutil;

import static de.dbo.tools.utils.print.Print.line;

import java.util.Date;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public final class SpringPrint {
    
    public static StringBuilder print(final AnnotationConfigApplicationContext ctx) {
  	if (null==ctx) {
  	    return new StringBuilder("AnnotationConfigApplicationContext is null!");
  	}
  	final StringBuilder sb = new StringBuilder("AnnotationConfigApplicationContext:");
  	sb.append("\n\t - ctx object:                 " + ctx.toString());
  	sb.append("\n\t - ctx class:                  " + ctx.getClass().getName());
  	sb.append("\n\t - application name:           " + ctx.getApplicationName());
  	sb.append("\n\t - application display name:   " + ctx.getDisplayName());
  	sb.append("\n\t - application startup-date:   " + new Date(ctx.getStartupDate()));
  	return sb;
    }
    
    public static final StringBuilder print(final Environment env) {
  	if (null==env) {
  	    return new StringBuilder("Environment is null!");
  	}
  	final StringBuilder sb = new StringBuilder("Environment:");
  	sb.append("\n\t - environment object: " + env.toString());
  	sb.append("\n\t - environment class:  " + env.getClass().getName());
  	sb.append("\n\t - profiles default:   " + (line(env.getDefaultProfiles())).toString().trim());
  	sb.append("\n\t - profiles active:    " + (line(env.getActiveProfiles())).toString().trim());
  	return sb;
    }
}
