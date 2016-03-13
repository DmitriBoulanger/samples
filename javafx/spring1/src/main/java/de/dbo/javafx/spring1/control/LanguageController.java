package de.dbo.javafx.spring1.control;


import de.dbo.javafx.spring1.model.Language;
import de.dbo.javafx.spring1.model.LanguageModel;

public class LanguageController {

	private LanguageModel model;
	
	public LanguageController(LanguageModel model){
		this.model = model;
		toEnglish();
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
	
}
