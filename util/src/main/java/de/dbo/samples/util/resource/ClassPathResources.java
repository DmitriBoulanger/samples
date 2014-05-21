package de.dbo.samples.util.resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ClassPathResources {

    /**
     * pattern examples: see the test
     *
     *
     * @param pattern
     * @return
     * @throws IOException
     */
    public static List<String> resourceFilenames(final String pattern) throws IOException {
        final PathMatchingResourcePatternResolver rr = new PathMatchingResourcePatternResolver();
        final Resource[] resources = rr.getResources("classpath:" + pattern);
		 final List<String> ret = new ArrayList<String>();
		 for (final Resource resource:resources) {
			 ret.add(resource.getFilename());
		 }
		 return ret;
	 }

    public static List<File> resourceFiles(final String pattern) throws IOException {
        final PathMatchingResourcePatternResolver rr = new PathMatchingResourcePatternResolver();
        final Resource[] resources = rr.getResources("classpath:" + pattern);
        final List<File> ret = new ArrayList<File>();
        for (final Resource resource : resources) {
            ret.add(resource.getFile());
        }
        return ret;
    }

}
