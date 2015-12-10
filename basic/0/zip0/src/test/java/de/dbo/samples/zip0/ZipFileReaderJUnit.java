package de.dbo.samples.zip0;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipFileReaderJUnit {
    private static final Logger log =  LoggerFactory.getLogger(ZipFileReaderJUnit.class);

    private static File data = new File("data/icons.zip");
    private static File out = new File("target/output");

    static {
	if (!out.exists()) {
	    out.mkdirs();
	}
    }

    @Test
    public void readZipDataUsingZipInputStream() throws Exception {
	log.info("Redaing ZIP-file" + data.getName() + " with ZipInputStream ...");
	ZipFileReader.readUsingZipInputStream(data, out);
    }

    @Test
    public void readZipDataUsingZipFile() throws Exception {
	log.info("Redaing ZIP-file " + data.getName() + " with ZipFile ...");
	ZipFileReader.readUsingZipFile(data, out);
    }
    
    @Test
    public void readEntries() throws Exception {
	log.info("Redaing entries from ZIP-file " + data.getName() + " and checking their size ...");
	assertEquals( ZipFileReader.getEntry(data, "Arbeitsamt.png").length, 554);
	assertEquals( ZipFileReader.getEntry(data, "Das Finanzamt Neuwied.ico").length, 318);
	assertEquals( ZipFileReader.getEntry(data, "Steuern.png").length, 193);
	assertEquals( ZipFileReader.getEntry(data, "РФ.png").length, 610);
	assertEquals( ZipFileReader.getEntry(data, "Elster.png").length, 384);
	assertEquals( ZipFileReader.getEntry(data, "Rente.png").length, 378);
    }
}
