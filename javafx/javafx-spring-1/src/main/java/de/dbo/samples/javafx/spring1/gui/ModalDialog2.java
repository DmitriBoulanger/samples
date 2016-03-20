package de.dbo.samples.javafx.spring1.gui;

import de.dbo.samples.javafx.spring1.control.Modal2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

public class ModalDialog2 extends Stage {
    private static Logger logger = LoggerFactory.getLogger(ModalDialog2.class);

	
	private Scene scene;

	public ModalDialog2(Modal2 controller, URL fxml, Window owner, ResourceBundle bundle) {
		this(controller, fxml, owner, StageStyle.DECORATED, Modality.APPLICATION_MODAL, bundle);
	}

	public ModalDialog2(final Modal2 controller, URL fxml, Window owner, StageStyle style, Modality modality, ResourceBundle bundle) {
		super(style);
		initOwner(owner);
		initModality(modality);
		FXMLLoader loader = new FXMLLoader(fxml);
		loader.setResources(bundle);
		try {
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				public Object call(Class<?> aClass) {
					return controller;
				}
			});
			controller.setDialog(this);
			scene= new Scene((Parent) loader.load());
			setScene(scene);
		} catch (IOException e) {
			logger.error("Error loading modal class", e);
			throw new RuntimeException(e);
		}
	}
	
	public ObservableList<String> getStyleSheets(){
		return scene.getStylesheets();
	}
	
}