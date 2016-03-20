package de.dbo.samples.javafx.spring1.gui.components2;

import de.dbo.samples.javafx.spring1.control.Modal2;
import de.dbo.samples.javafx.spring1.gui.ScreensConfig2;
import de.dbo.samples.javafx.spring1.model.MessageModel;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PopupPresentation extends Modal2 {

	public PopupPresentation(ScreensConfig2 config) {
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
