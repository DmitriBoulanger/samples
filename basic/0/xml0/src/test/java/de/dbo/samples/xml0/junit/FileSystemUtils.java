package de.dbo.samples.xml0.junit;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

final class FileSystemUtils {
	 private static final String TEST_DIR = "src/test/resources/de/dbo/samples/xml0/dtd/";
		
	private FileSystemUtils() {
		// no instances of this class
	}
	
	static final String read(final String name) throws IOException {
    	 final Charset charset = Charset.forName("utf-8");
    	 
         final Path pathXml = FileSystems.getDefault().getPath(TEST_DIR, name);
         return new String(Files.readAllBytes(pathXml),charset);
    }
    
   static final Path path(final String name) throws IOException {
        return FileSystems.getDefault().getPath(TEST_DIR, name);
   }

}
