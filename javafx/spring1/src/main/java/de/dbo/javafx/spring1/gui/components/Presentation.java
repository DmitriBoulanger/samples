package de.dbo.javafx.spring1.gui.components;

import de.dbo.javafx.spring1.gui.ScreensConfig;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public abstract class Presentation  {

	protected ScreensConfig config;
	
	public Presentation(ScreensConfig config) {
		this.config = config;
	}
	
	public Presentation() {
		
	}
	
	public void init() {
	    
	}

	//
	// Getters and Setters
	//
	
	public ScreensConfig getConfig() {
	    return config;
	}

	public void setConfig(ScreensConfig config) {
	    this.config = config;
	}
	
	
}
