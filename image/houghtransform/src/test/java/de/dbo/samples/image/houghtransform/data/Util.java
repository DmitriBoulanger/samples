package de.dbo.samples.image.houghtransform.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Util {
	private static final Logger log = LoggerFactory.getLogger(Util.class);

	static final String PNG = ".png";

    static final List<String> filenames(final String resource) throws Exception {
        log.trace("reading file-names from " + resource + " ...");
        try {
        	final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        	final InputStream inputStream = classLoader.getResourceAsStream(resource);
            final List<String> ret = IOUtils.readLines(inputStream, Charsets.UTF_8);
            inputStream.close();
            final Set<Object> nonImages = new HashSet<Object>();
            for (final String name:ret) {
            	if (!name.endsWith(PNG)) {
            		nonImages.add(name);
            	}
            }
            ret.removeAll(nonImages);
            log.debug("reading file-names from " + resource + ": " + ret.size() + " names found");
            return ret;
        }
        catch(Exception e) {
            throw new Exception("Can't read file-names from " + resource,e);
        }
    }

    private static final String PERFORMANCE_PROPERTIES_RESOURCE = "performance.properties";
    private static final String PERFORMANCE_SUBDIR_PROPERTY_SUFFIX = ".performanceSubdir";
    private static Properties PERFORMANCE_PROPERTIES = new Properties();

    static final String performanceSubdir(final ImageCollectionCatalog collection)  {
    	final String key;
    	switch (collection) {

    	  case SIGNATURE_PERFORMANCE_UNCHECKED:
          case SIGNATURE_PERFORMANCE_CHECKED:
            case SIGNATURE_PERFORMANCE_ERROR_UNCHECKED:
            case SIGNATURE_PERFORMANCE_ERROR_CHECKED:
        	  key = "signature" + PERFORMANCE_SUBDIR_PROPERTY_SUFFIX;
        	  break;

        	  default:
        		  return null;
    	}

    	loadPerformanceProperties();
    	if (PERFORMANCE_PROPERTIES.isEmpty()) {
    		log.warn(PERFORMANCE_PROPERTIES_RESOURCE + " is empty");
      	  return null;
    	}
    	final String ret = PERFORMANCE_PROPERTIES.getProperty(key);
        if (null==ret) {
        	  log.warn(PERFORMANCE_PROPERTIES_RESOURCE + " has no value for " + key);
        	  return null;
        }
        return ret;
    }

    private static final void loadPerformanceProperties()  {
    	if (!PERFORMANCE_PROPERTIES.isEmpty()) {
    		return;
    	}
    	final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
        	final InputStream inputStream = classLoader.getResourceAsStream(PERFORMANCE_PROPERTIES_RESOURCE);
        	if (null==inputStream) {
        		log.warn("Resource "+ PERFORMANCE_PROPERTIES_RESOURCE+ " not found");
        		return;
        	}
        	PERFORMANCE_PROPERTIES.load(inputStream);
        	inputStream.close();
        }
        catch(IOException e) {
        	log.warn("Can't read/load resource "+ PERFORMANCE_PROPERTIES_RESOURCE+":"  + e.toString());
        }
    }

}
