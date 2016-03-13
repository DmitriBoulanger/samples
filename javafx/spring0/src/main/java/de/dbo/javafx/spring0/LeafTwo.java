package de.dbo.javafx.spring0;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class LeafTwo extends GridPane {
	
	public LeafTwo() {
		Pane content = new Pane();
		GridPane.setHgrow(content, Priority.ALWAYS);
		GridPane.setVgrow(content, Priority.ALWAYS);
		content.getChildren().add(new Label("right"));
		this.getChildren().add(content);
	}

} 