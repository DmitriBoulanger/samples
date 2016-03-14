package de.dbo.javafx.spring0.components;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class LeafOne extends GridPane {
	
    public LeafOne() {
	Pane content = new Pane();
	GridPane.setHgrow(content, Priority.ALWAYS);
	GridPane.setVgrow(content, Priority.ALWAYS);
	final Label label = new Label("left");
	label.setId("leftLabel");
	content.getChildren().add(new Label("left"));
	this.getChildren().add(content);
    }

} 