package de.dbo.javafx.spring1.control;

import de.dbo.javafx.spring1.gui.ModalDialog2;
import de.dbo.javafx.spring1.gui.ScreensConfig2;

public abstract class Modal2  {

    protected ScreensConfig2 config;
    
	protected ModalDialog2 dialog;

	public Modal2(ScreensConfig2 config) {
		this.config = config;
	}

	public void setDialog(ModalDialog2 dialog) {
		this.dialog = dialog;
	}
}