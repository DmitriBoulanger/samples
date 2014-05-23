package de.dbo.samples.image.houghtransform.core;

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

public final class Imagetrace {
	private static final Logger log = LoggerFactory.getLogger(Imagetrace.class);

	private static final File IMAGETRACE_DIR = imagetraceDir();
	private static int IMAGE_CNT = 0;

	private static final File imagetraceDir() {
		final String resource = "imagetrace.properties";
		final InputStream inputStream = 
				ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
		if (null != inputStream) {
			final Properties imagetrace = new Properties();
			try {
				imagetrace.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				log.error("Can't read " + resource + ": ", e);
				return null;
			}

			if (imagetrace.isEmpty()) {
				log.warn(resource + " has no properties");
				return null;
			}
			final SimpleDateFormat sdf = 
					new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
			final String extension = sdf.format(new Date());
			final File dir = new File(imagetrace.getProperty("dir") + "-"
					+ extension);
			final boolean created = dir.mkdirs();

			if (created) {
				for (final Category category : Category.values()) {
					if (!categorySubdir(dir,category).mkdirs()) {
						log.error(" Can't create sub-directory for " + category.name());
						return null;
					}
				}
				return dir;
			} else {
				log.error(" Can't create directory " + dir);
				return null;
			}
		} else {
			log.info(resource + " not found");
			return null;
		}
	}

   static final Category save(final BufferedImage image, final Category category) {
		if (null != IMAGETRACE_DIR) {
			final File file = new File(categorySubdir(IMAGETRACE_DIR,category),
					new DecimalFormat("000").format(IMAGE_CNT) + ".png");
			try {
				ImageIO.write(image, "png", file);
				IMAGE_CNT++;
			} catch (Exception e) {
				log.error("Can't save image as " + file);
			}
		}
		return category;
	}
   
   private static final File categorySubdir(File parentDir, Category category) {
	   return new File(parentDir, category.name().toLowerCase());
   }

}
