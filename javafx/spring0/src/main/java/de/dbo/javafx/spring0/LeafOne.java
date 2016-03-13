package de.dbo.javafx.spring0;

import java.util.List;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.application.*;

public class LeafOne extends GridPane {
	
	public LeafOne() {
		Pane content = new Pane();
		GridPane.setHgrow(content, Priority.ALWAYS);
		GridPane.setVgrow(content, Priority.ALWAYS);
		content.getChildren().add(new Label("left"));
		this.getChildren().add(content);
	}

} 