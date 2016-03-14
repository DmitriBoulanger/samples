package de.dbo.javafx.spring0;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChild;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;

import de.dbo.javafx.spring0.components.RootComponent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpringFXtestfx extends ApplicationTest  {
    private static final Logger log = LoggerFactory.getLogger(SpringFXtestfx.class);
    
    @Override
    public void start(Stage stage) {
	final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	final RootComponent root = (RootComponent) ctx.getBean("root");
	ctx.close();
	
	final double height = root.getRootHeight();
	final double width = root.getRootWidth();
	
	final Group group = new Group();
	group.getChildren().add(root);
	
	final Scene scene = new Scene(group, width, height);
	stage.setScene(scene);
	
	log.debug("Application starting ...");
	stage.show();
	
    }
    
    @Test
    public void rootComponentAndItsChildren() {
        verifyThat("#rootPane", isNotNull());
        verifyThat("#leftPane", isNotNull());
        verifyThat("#rightPane", isNotNull());
        
        verifyThat("#rootPane", hasChild("#leftPane"));
        verifyThat("#rootPane", hasChild("#rightPane"));
        
//        verifyThat("#leftLabel", hasText("left"));
        

    }

} 


