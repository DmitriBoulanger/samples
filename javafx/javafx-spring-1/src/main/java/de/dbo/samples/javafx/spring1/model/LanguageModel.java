package de.dbo.samples.javafx.spring1.model;

import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;

public class LanguageModel extends Observable{
	
	private ResourceBundle bundle;
	private Language lang;
	
	public LanguageModel(){
		init();
	}
	
	public void init() {
	    setBundle(Language.EN);
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(Language lang) {
		if ( lang==null || lang.equals(this.bundle)) return;
		setLanguage(lang);
		bundle = ResourceBundle.getBundle("lang/lang", new Locale(lang.getValue(), lang.toString()));
		setChanged();
		notifyObservers();
	}

	public Language getLanguage() {
		return lang;
	}

	private void setLanguage(Language lang) {
		this.lang = lang;
	}

	

}
