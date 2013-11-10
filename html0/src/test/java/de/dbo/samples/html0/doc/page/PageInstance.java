package de.dbo.samples.html0.doc.page;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class PageInstance extends Page{
	
	PageInstance(final boolean xhtml) {
		super("Documentation page", xhtml);
		summary(
				"This is a very nice documentation page");
		section("Important remarks");
		p("With Java 7 it is very easy to read and write files. "
				+ "If you want to put a string into a file, "
				+ "then you can do something");
		p("In order to correctly read and write text files, "
				+ "you need to understand that those read/write operations always "
				+ "use an implicit character encoding to translate raw bytes "
				+ "- the 1s and 0s - into text. ");
		section("Best praxis");
		p("f it doesn't, then the best it can do is make an assumption. "
				+ "Problems with encoding usually show up "
				+ "as weird characters in a tool that has read the file. ");
		
		text("u kannst den Nutzernamen für deine persönliche Chronik nur einmal ändern. "
				+ "Melde dich auf einem Computer "
				+ "bei Facebook an und klicke auf das ...");
		nl();
		text("Also, wenn du den normalen Namen meinst, dann weiß ich wie Du gehst "
				+ "auf den Pfeil oben wo du dich abmeldest und dann auf Kontoeinstellungen ...");
		text("Hei Leute ich suche eine Möglichkeit das ich den Nutzername bei "
				+ "Facebok ändern kann ohne das ich dabei meine Handynummer zum ...");
		final List list = list("Some decisions:");
		list.item("Very good");
		list.item("Very bad");
		
		section("Conclusion");
		text("ja es kommt erst die meldung das du nun registriert bist. aber um diese "
				+ "vollständig abzuschliessen musst du erst noch in deinem emailpostfach");
		text("Und siehe da, er konnte auf einmal den Nutzernamen ändern. Was ist das für eine Logik, "
				+ "bin ich denn kein Administrator auch wenn es mein ...");
		textem("Standardmässig erhält der Admin-Benutzer einer WordPress ");
	}
	
	private static final String TARTGET_DATA_DIR =  new File(".","/target/data/").getAbsolutePath();

	void save() throws IOException {
		final Path targetDir = Paths.get(TARTGET_DATA_DIR);
		if (!Files.exists(targetDir)) {
			Files.createDirectories(targetDir);
		}
		final byte[] text = toString().getBytes();
		final String pagePath = new File(TARTGET_DATA_DIR
				, getClass().getSimpleName()+".html").getAbsolutePath();
		final Path target = Paths.get(pagePath);
		Files.deleteIfExists(target);
		Path file = Files.createFile(target);
		Files.write(file ,text, StandardOpenOption.WRITE);
	}
	
	
}
