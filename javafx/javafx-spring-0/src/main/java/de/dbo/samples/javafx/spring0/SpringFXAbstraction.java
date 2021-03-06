package de.dbo.samples.javafx.spring0;

 import javafx.scene.*;
 import javafx.stage.*;
 import javafx.application.*;
 
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class SpringFXAbstraction extends Application {
	private ClassPathXmlApplicationContext context = null;
	private final String springXML;
	private Node root;
	private final String rootName;

	public SpringFXAbstraction(final String springXML) {
		this.springXML = springXML;
		this.rootName = null;
		this.context = new ClassPathXmlApplicationContext(
				new String[] { springXML });
	}

	@Override
	public final void start(Stage stage) throws Exception {
		root = (Node) this.context.getBean(rootName!=null?rootName:"root");
		startFXApplication(stage);
	}
	
	public final String getSpringXML() {
		return springXML;
	}
	
	public final Node getRoot() {
		return root;
	}

	public abstract void startFXApplication(Stage stage);
}