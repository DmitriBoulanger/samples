package de.dbo.samples.javafx.spring0;

 import de.dbo.samples.javafx.spring0.components.RootComponent;

import org.slf4j.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpringFX extends SpringFXAbstraction{
    private static final Logger log = LoggerFactory.getLogger(SpringFX.class);
	
    protected SpringFX(final String springCtx) {
	super(springCtx);
    }
 
    @Override
    public void startFXApplication(Stage stage) {
	log.debug("Application starting ...");
	final RootComponent root = (RootComponent) this.getRoot();
	final double height = root.getRootHeight();
	final double width = root.getRootWidth();
	log.debug("Application size="+width+"x"+height);
	
	final Group group = new Group();
	group.getChildren().add(root);
	
	final Scene scene = new Scene(group, width, height, Color.BLACK);		    
	scene.getStylesheets().add(root.getCss());
	
	stage.setScene(scene);
	stage.show();
	log.debug("Application started");
    }

} 