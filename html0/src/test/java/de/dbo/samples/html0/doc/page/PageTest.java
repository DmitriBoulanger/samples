package de.dbo.samples.html0.doc.page;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Before;
import org.junit.Test;

public class PageTest {
	
	private static final String DIR =  new File(".").getAbsolutePath()+"/target/data/";
	private static Path targetDir;
	
	@Before 
	public void init() throws IOException {
		targetDir = Paths.get(DIR);
		if (Files.exists(targetDir)) {
			return;
		}
		try {
			Files.deleteIfExists(targetDir);
		} catch (DirectoryNotEmptyException e) {
			 return;
		}
		 Files.createDirectories(targetDir);
	}
	
	@Test
	public void test() throws IOException {
		final Path target = Paths.get(new File(DIR,Page.class.getSimpleName()+".html").getAbsolutePath());
		Files.deleteIfExists(target);
		Path file = Files.createFile(target);
		final Page page = new Page("Documentation page");
		page.summary(
				"This is a very nice documentation page");
		page.section("Important remarks");
		page.p("With Java 7 it is very easy to read and write files. "
				+ "If you want to put a string into a file, "
				+ "then you can do something");
		page.p("In order to correctly read and write text files, "
				+ "you need to understand that those read/write operations always "
				+ "use an implicit character encoding to translate raw bytes "
				+ "- the 1s and 0s - into text. ");
		page.section("Best praxis");
		page.p("f it doesn't, then the best it can do is make an assumption. "
				+ "Problems with encoding usually show up "
				+ "as weird characters in a tool that has read the file. ");
		Files.write(file
		    ,page.toString().getBytes()
		    , StandardOpenOption.WRITE);
	}

}
