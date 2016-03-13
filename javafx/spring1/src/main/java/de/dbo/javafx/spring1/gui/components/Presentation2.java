package de.dbo.javafx.spring1.gui.components;

import de.dbo.javafx.spring1.gui.ScreensConfig;
import de.dbo.javafx.spring1.gui.ScreensConfig2;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public abstract class Presentation2 extends StackPane {

	protected ScreensConfig2 config;
	
	protected List<Node> leafs;

	public Presentation2(ScreensConfig2 config) {
		this.config = config;
	}
	
	public Presentation2() {
		
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
