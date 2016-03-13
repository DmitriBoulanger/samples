package de.dbo.javafx.spring0;

import java.util.List;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.application.*;

public class LeafTwo extends GridPane {
	
	public LeafTwo() {
		Pane content = new Pane();
		GridPane.setHgrow(content, Priority.ALWAYS);
		GridPane.setVgrow(content, Priority.ALWAYS);
		content.getChildren().add(new Label("right"));
		this.getChildren().add(content);
	}

} 