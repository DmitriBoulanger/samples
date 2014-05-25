package de.dbo.samples.image.houghtransform;

import de.dbo.samples.image.houghtransform.api.Category;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tracer of categorized images
 * If tracer is enabled, it saves categorized images in the
 * directory specified in imagetrace.properties.
 * If imagetrace.properties doesn't exist or it doesn't contain expected property, 
 * then this tracer is disabled (no saving is being done).
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class CaregorizerImageTracer {
	private static final Logger log = LoggerFactory.getLogger(CaregorizerImageTracer.class);

	private static final String IMAGETRACE_PROPERTIES = "imagetrace.properties";
	private static final String IMAGETRACE_DIR_EXTENSION = "yyyy-MM-dd-HH-mm-ss-SSS";
	private static final String IMAGETRACE_DIR_PROPERTY = "dir";
	private static final String IMAGETRACE_TIMESTAMP_PROPERTY = "timestamp";
	private static final String IMAGETRACE_OUTPUT_FORMAT = "png";
	private static final File IMAGETRACE_DIR = imagetraceDir();
	private static Object IMAGETRACE_LOCK = new Object();
	
	private static int IMAGETRACE_CNT = 0;

	private CaregorizerImageTracer() {
		
	}

	/**
	 * Root-directory to accumulate categorized images.
	 * This method performs initialization of the tracer.
	 * Non-null directory enables the tracer
	 * 
	 * @return root directory to save categorized images only if it is 
	 *  enabled in the file imagetrace.properties; otherwise null.
	 *  Null-directory disables image-tracing
	 */
	private static final File imagetraceDir() {
		final InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(IMAGETRACE_PROPERTIES);
		if (null == inputStream) {
			log.debug("Resource " + IMAGETRACE_PROPERTIES + " not found");
			return null;
		}

		final Properties imagetrace = new Properties();
		try {
			imagetrace.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			log.error("Can't read/load data in " + IMAGETRACE_PROPERTIES + ": ", e);
			return null;
		}

		if (imagetrace.isEmpty()) {
			log.debug(IMAGETRACE_PROPERTIES + " has no properties");
			return null;
		}
		final String dirValue = imagetrace.getProperty(IMAGETRACE_DIR_PROPERTY);
		if (null == dirValue || 0 == dirValue.trim().length()) {
			log.debug(IMAGETRACE_PROPERTIES + " has no value for property " + IMAGETRACE_DIR_PROPERTY);
			return null;
		}
		final String timestampValue = imagetrace.getProperty(IMAGETRACE_TIMESTAMP_PROPERTY);
		final String timestampValueTrimmed;
		if (null == timestampValue || 0 == timestampValue.trim().length()) {
			log.debug(IMAGETRACE_PROPERTIES + " has no value for property "+ IMAGETRACE_TIMESTAMP_PROPERTY);
			timestampValueTrimmed = IMAGETRACE_DIR_EXTENSION;
		} else {
			timestampValueTrimmed = timestampValue.trim();
		}
		final String dirValueTrimmed = dirValue.trim();
		final SimpleDateFormat sdf = new SimpleDateFormat(timestampValueTrimmed);
		final String timestamp = "-" + sdf.format(new Date());

		final String dirPath = new File(dirValueTrimmed + timestamp).getAbsolutePath();
		final File dir = new File(dirPath);
		if (dir.exists()) {
			log.error("Directory " + dirPath + " exits. Image-tracing disabled");
			return null;
		}
		final boolean created = dir.mkdirs();
		if (created) {
			for (final Category category : Category.values()) {
				if (!categorySubdir(dir, category).mkdirs()) {
					log.error(" Can't create sub-directory for category " + category.name());
					return null;
				}
			}
			log.info("Image-tracing enabled. Directory " + dirPath);
			return dir;
		} else {
			log.error(" Can't create directory " + dirPath);
			return null;
		}

	}

	/**
	 * saves categorized image as PNG-file in the directory 
	 * derived from imagetrace.properties if it is not null
	 * 
	 * @param image image to be saved
	 * @param category category of the image
	 * @return image-category
	 */
	public static final Category save(final BufferedImage image, final Category category) {
		return save(IMAGETRACE_DIR,image,category);
	}
	
	/**
	 * saves categorized image as PNG-file in the specified directory if it is not null
	 * 
	 * @param directory to save categorized images
	 * @param image image to be saved
	 * @param category category of the image
	 * @return image-category
	 */
	public static final Category save(final File directory
			, final BufferedImage image, final Category category) {
		if ( null == directory ) {
			return category;
		}
		synchronized (IMAGETRACE_LOCK) {
			final String filename = new DecimalFormat("000").format(IMAGETRACE_CNT) + ".png";
			final File file = new File(categorySubdir(directory, category),filename);
			try {
				ImageIO.write(image, IMAGETRACE_OUTPUT_FORMAT, file);
				IMAGETRACE_CNT++;
				if ( file.exists() ) {
					log.trace("saved " + file.getAbsolutePath());
				} else {
					log.error("Image " + file + " was NOT saved!");
				}
			} catch (Exception e) {
				log.error("Can't save image as " + file);
			}
		}
		return category;
	}
   
   private static final File categorySubdir(final File directory, final Category category) {
	   return new File(directory, category.name().toLowerCase());
   }
}
