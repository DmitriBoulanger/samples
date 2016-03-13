package de.dbo.javafx.spring1.control;

import de.dbo.javafx.spring1.gui.ModalDialog;
import de.dbo.javafx.spring1.gui.ScreensConfig;
import de.dbo.javafx.spring1.gui.components.Presentation;

public abstract class Modal extends Presentation {

	protected ModalDialog dialog;

	public Modal(ScreensConfig config) {
		super(config);
	}

	public void setDialog(ModalDialog dialog) {
		this.dialog = dialog;
	}
}