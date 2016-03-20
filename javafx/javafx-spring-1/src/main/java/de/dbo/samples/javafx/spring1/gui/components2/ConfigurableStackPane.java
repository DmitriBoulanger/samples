package de.dbo.samples.javafx.spring1.gui.components2;

import de.dbo.samples.javafx.spring1.gui.ScreensConfig2;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public abstract class ConfigurableStackPane extends StackPane  {

	protected ScreensConfig2 config;
	
	protected List<Node> leafs;

	public ConfigurableStackPane(ScreensConfig2 config) {
		this.config = config;
	}
	
	public ConfigurableStackPane() {
		
	}
	
	public void init() {
	    super.getChildren().addAll(leafs);
	}

	//
	// Getters and Setters
	//
	
	public ScreensConfig2 getConfig() {
	    return config;
	}

	public void setConfig(ScreensConfig2 config) {
	    this.config = config;
	}
	
	
}
