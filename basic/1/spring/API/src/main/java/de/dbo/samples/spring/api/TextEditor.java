package de.dbo.samples.spring.api;

public interface TextEditor {

	// setter method  
	public abstract void setSpellChecker(SpellChecker spellChecker);

	// getter method  
	public abstract SpellChecker getSpellChecker();

	public abstract void spellCheck();

}