package de.dbo.javafx.spring1.gui.components;

import de.dbo.javafx.spring1.control.LanguageController;
import de.dbo.javafx.spring1.gui.ScreensConfig;
import de.dbo.javafx.spring1.gui.ScreensConfig2;
import de.dbo.javafx.spring1.model.Language;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class FirstPresentation2 extends Presentation2 {

	RadioButton engRadio;
	
 
	RadioButton romRadio;
	
 
	ToggleGroup langGroup;
	
	@FXML
	void nextView(ActionEvent event){
		config.loadSecond();
	}
	
	private LanguageController languageController;
	
	
	public FirstPresentation2(ScreensConfig2 config) {
		super(config);
	}
	
	public FirstPresentation2() {
		
	}
	
	public void init() {
	    initialize();
	}
	
	@FXML
	void initialize() {
		if (Language.RO.equals(languageController.getLanguage())){
			engRadio.setSelected(false);
			romRadio.setSelected(true);
		}
		
		langGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				changeLanguage();
			}
		});
	}
	
	private void changeLanguage() {
		if (engRadio.isSelected())
			languageController.toEnglish();
		else
			languageController.toRomanian();
	}

}
