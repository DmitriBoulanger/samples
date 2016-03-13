package de.dbo.javafx.spring1.gui.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import de.dbo.javafx.spring1.control.Modal;
import de.dbo.javafx.spring1.control.Modal2;
import de.dbo.javafx.spring1.gui.ScreensConfig;
import de.dbo.javafx.spring1.gui.ScreensConfig2;
import de.dbo.javafx.spring1.model.MessageModel;

import org.springframework.beans.factory.annotation.Autowired;

public class PopupPresentation2 extends Modal2 {

	public PopupPresentation2(ScreensConfig2 config) {
		super(config);
	}
	
	@Autowired
	private MessageModel model;
	
	@FXML
	TextField messageTf;
	
	@FXML
	void initialize() {
		messageTf.setText(model.getMessage());
	}
	
	@FXML 
	void clickedOk(ActionEvent event){
		dialog.close();
	}
	
	@FXML 
	void onApply(ActionEvent event){
		model.setMessage(messageTf.getText());
	}
	

}
