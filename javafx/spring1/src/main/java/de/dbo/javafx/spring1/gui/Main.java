package de.dbo.javafx.spring1.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import de.dbo.javafx.spring1.config.AppConfig;
import de.dbo.javafx.spring1.model.LanguageModel;

import org.slf4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Main extends Application {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		logger.info("Starting application");

		Platform.setImplicitExit(true);

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ScreensConfig screens = context.getBean(ScreensConfig.class);
		LanguageModel lang = context.getBean(LanguageModel.class);
		
		screens.setLangModel(lang);
		screens.setPrimaryStage(stage);
		screens.showMainScreen();
		screens.loadFirst();

	}
}
