package de.dbo.samples.util.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.Resource;

public class ClassPathResources {

	 public static List<String> resources() throws IOException {
		 final PathMatchingResourcePatternResolver rr = new PathMatchingResourcePatternResolver();
		 final Resource[] resources = rr.getResources("classpath*:**/*.class");
		 final List<String> ret = new ArrayList<String>();
		 for (final Resource resource:resources) {
			 ret.add(resource.getFilename());
			 System.out.println(resource.getFile());
			
		 }
		 return ret;
	 }
	 
	 public static final void main(String[] args) throws IOException {
		 resources();
	 }

}
