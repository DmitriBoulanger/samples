package de.dbo.javafx.spring1.gui.components;

import de.dbo.javafx.spring1.gui.ScreensConfig;
import de.dbo.javafx.spring1.gui.ScreensConfig2;
import de.dbo.javafx.spring1.model.MessageModel;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;

public class SecondPresentation2 extends Presentation2 {

	public SecondPresentation2(ScreensConfig2 config) {
		super(config);
	}
	
	@Autowired
	private MessageModel model;
	
	@FXML
	TextField messageTf;
	
	@FXML
	void initialize() {
		messageTf.setText(model.getMessage());
		
		model.addObserver(new Observer() {
			
			public void update(Observable o, Object arg) {
				messageTf.setText(model.getMessage());
			}
		});
	}
	
	@FXML 
	void onApply(ActionEvent event){
		model.setMessage(messageTf.getText());
	}
	
	@FXML
	void prevView(ActionEvent event){
		config.loadFirst();
	}
	
	@FXML 
	void openPopup(ActionEvent event){
		config.loadPopup();
	}
	

}
