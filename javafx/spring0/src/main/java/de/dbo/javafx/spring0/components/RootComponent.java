package de.dbo.javafx.spring0.components;

import java.util.List;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.application.*;

public class RootComponent extends SplitPane {
	private List<Node> leafs;
	
	public RootComponent() {
		this.setPrefSize(800, 600);
	}

	public List<Node> getLeafs() {
		return leafs;
	}

	public void setLeafs(List<Node> leafs) {
		this.leafs = leafs;
	}
}
