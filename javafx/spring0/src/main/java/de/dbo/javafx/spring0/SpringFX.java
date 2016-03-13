package de.dbo.javafx.spring0;

 import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpringFX extends ASpringFX{
	
	public SpringFX() {
		super("main.xml");
	}

	public static void main(final String[] args) {
		Application.launch(args);
	}

	@Override
	public void startFXApplication(Stage stage) {
		Group group = new Group();
	    Scene scene = new Scene(group, 800, 600, Color.BLACK);		    
	    RootComponent root = (RootComponent) this.getRoot();
	    List<Node> leafs = root.getLeafs();
	    root.getItems().addAll(leafs);	    
		group.getChildren().add(root);
		stage.setScene(scene);
		stage.show();
	}

} 