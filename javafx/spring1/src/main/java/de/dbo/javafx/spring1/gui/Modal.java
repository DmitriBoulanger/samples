package de.dbo.javafx.spring1.gui;

public abstract class Modal extends Presentation {

	protected ModalDialog dialog;

	public Modal(ScreensConfig config) {
		super(config);
	}

	public void setDialog(ModalDialog dialog) {
		this.dialog = dialog;
	}
}