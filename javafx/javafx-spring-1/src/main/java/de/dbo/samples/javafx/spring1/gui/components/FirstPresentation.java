package de.dbo.samples.javafx.spring1.gui.components;

import de.dbo.samples.javafx.spring1.control.LanguageController;
import de.dbo.samples.javafx.spring1.gui.Configurable;
import de.dbo.samples.javafx.spring1.gui.ScreensConfig;
import de.dbo.samples.javafx.spring1.model.Language;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class FirstPresentation implements Configurable {

	public FirstPresentation(ScreensConfig config) {
		this.config = config;
	}
	
	@FXML
	RadioButton engRadio, romRadio;
	
	@FXML
	ToggleGroup langGroup;
	
	@Autowired
	private LanguageController langCtr;
	
	@FXML
	void nextView(ActionEvent event){
		config.loadSecond();
	}
	
	@FXML
	void initialize() {
		if (Language.RO.equals(langCtr.getLanguage())){
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
			langCtr.toEnglish();
		else
			langCtr.toRomanian();
	}
	

	
	
	
	protected ScreensConfig config;
	
	 
	@Override
	public void init() {
	    
	}

	//
	// Getters and Setters
	//
	
	@Override
	public ScreensConfig getConfig() {
	    return config;
	}

	@Override
	public void setConfig(ScreensConfig config) {
	    this.config = config;
	}

}
