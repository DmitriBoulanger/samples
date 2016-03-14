package de.dbo.javafx.spring1.gui.components;

import de.dbo.javafx.spring1.gui.Configurable;
import de.dbo.javafx.spring1.gui.ScreensConfig;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public abstract class Presentation implements Configurable  {

	protected ScreensConfig config;
	
	public Presentation(ScreensConfig config) {
		this.config = config;
	}
	
	public Presentation() {
		
	}
	
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
