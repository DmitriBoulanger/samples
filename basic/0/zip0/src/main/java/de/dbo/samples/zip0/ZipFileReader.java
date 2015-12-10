package de.dbo.samples.zip0;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java program to iterate and read file entries from Zip archive. This program
 * demonstrate two ways to retrieve files from Zip using ZipFile and by using
 * ZipInputStream class.
 *           
 * Read more:
 * http://javarevisited.blogspot.com/2014/06/2-examples-to-read-zip-files-in-java-zipFile-vs-zipInputStream.html#ixzz3twRpwlai
 *
 * @author Dmitri Boulanger, Hombach
 * 
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 * 
 */
public class ZipFileReader {
    private static final Logger log = LoggerFactory.getLogger(ZipFileReader.class);

    private static final int BUFFER_SIZE = 8192;

    public static byte[] getEntry(final File zipFile, final String name) throws IOException {
	final ZipFile file = new ZipFile(zipFile);
	final ZipEntry entry = file.getEntry(name);
	try {
	    return readFully(file.getInputStream(entry));
	} finally {
	    file.close();
	}
    }

    /*
     * Example of reading Zip archive using ZipFile class
     */

    public static void readUsingZipFile(final File zipFile, final File outputDir) throws IOException {
	final ZipFile file = new ZipFile(zipFile);
	log.info("Iterating over zip file : " + zipFile.getName());

	try {
	    final Enumeration<? extends ZipEntry> entries = file.entries();
	    while (entries.hasMoreElements()) {
		final ZipEntry entry = entries.nextElement();
		log.info(print(entry));
		extractEntry(entry.getName(), file.getInputStream(entry), outputDir);
	    }
	    log.info("Zip file [" + zipFile.getName() + "] extracted successfully in " + outputDir.getAbsolutePath());
	} finally {
	    file.close();
	}

    }

    /*
     * Example of reading Zip file using ZipInputStream in Java.
     */

    public static void readUsingZipInputStream(final File zipFile, final File outputDir) throws IOException {
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFile));
	final ZipInputStream is = new ZipInputStream(bis);

	try {
	    ZipEntry entry;
	    while ((entry = is.getNextEntry()) != null) {
		log.info(print(entry));
		extractEntry(entry.getName(), is, outputDir);
	    }
	} finally {
	    is.close();
	}
    }

    /*
     * Utility method to read data from InputStream
     */

    private static void extractEntry(final String entryName, InputStream is, final File outputDir) throws IOException {
	final File exractedFile = new File(outputDir, entryName);

	FileOutputStream fos = null;
	try {
	    fos = new FileOutputStream(exractedFile);
	    final byte[] buf = new byte[BUFFER_SIZE];
	    int length;
	    while ((length = is.read(buf, 0, buf.length)) >= 0) {
		fos.write(buf, 0, length);
	    }
	} catch (IOException e) {
	    log.error("Failure reading data-stream: ", e.getMessage());
	    throw e;
	} finally {
	    if (null != fos) {
		fos.close();
	    }
	}

    }

    private static byte[] readFully(final InputStream input) throws IOException {
	byte[] buffer = new byte[8192];
	int bytesRead;
	ByteArrayOutputStream output = new ByteArrayOutputStream();
	while ((bytesRead = input.read(buffer)) != -1) {
	    output.write(buffer, 0, bytesRead);
	}
	return output.toByteArray();
    }

    private static final String print(final ZipEntry entry) {
	return "File: [" + entry.getName() + "] Size=" + entry.getSize() + "  Modified on " + new Date(entry.getTime());
    }

}
