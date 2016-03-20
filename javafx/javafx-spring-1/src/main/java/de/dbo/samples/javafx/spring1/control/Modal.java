package de.dbo.samples.javafx.spring1.control;

import de.dbo.samples.javafx.spring1.gui.Configurable;
import de.dbo.samples.javafx.spring1.gui.ModalDialog;
import de.dbo.samples.javafx.spring1.gui.ScreensConfig;

public abstract class Modal implements Configurable {

	protected ModalDialog dialog;

	public Modal(ScreensConfig config) {
	    this.config = config;
	}

	public void setDialog(ModalDialog dialog) {
		this.dialog = dialog;
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