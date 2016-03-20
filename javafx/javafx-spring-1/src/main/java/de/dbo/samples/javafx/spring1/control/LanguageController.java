package de.dbo.samples.javafx.spring1.control;

import de.dbo.samples.javafx.spring1.model.Language;
import de.dbo.samples.javafx.spring1.model.LanguageModel;

public class LanguageController {

	private LanguageModel model;
	
	public LanguageController(LanguageModel model){
		this.model = model;
		init();
	}
	
	public void init() {
	    toEnglish();
	}
	
	public LanguageController(){
		init();
	}
	
	public void toEnglish(){
		model.setBundle(Language.EN);
	}
	
	public void toRomanian(){
		model.setBundle(Language.RO);
	}

	public Language getLanguage() {
		return model.getLanguage();
	}

	//
	// Getters and Setters
	//
	
	public LanguageModel getModel() {
	    return model;
	}

	public void setModel(LanguageModel model) {
	    this.model = model;
	}
	
}
