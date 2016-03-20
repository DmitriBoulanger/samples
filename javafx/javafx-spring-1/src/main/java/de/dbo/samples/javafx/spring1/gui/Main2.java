package de.dbo.samples.javafx.spring1.gui;

import de.dbo.samples.javafx.spring1.model.LanguageModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

@Service
public class Main2 extends Application {
	private static Logger log = LoggerFactory.getLogger(Main2.class);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
	    log.debug("Application starting ...");

	    Platform.setImplicitExit(true);

	    //		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	    final ClassPathXmlApplicationContext ctx = 
		    new ClassPathXmlApplicationContext("spring-application-configuration.xml");

	    ScreensConfig screens = ctx.getBean(ScreensConfig.class);
	    LanguageModel lang = ctx.getBean(LanguageModel.class);


	    //		screens.setLangModel(lang);
	    screens.setPrimaryStage(stage);
	    screens.showMainScreen();
	    screens.loadFirst();

	}
}
