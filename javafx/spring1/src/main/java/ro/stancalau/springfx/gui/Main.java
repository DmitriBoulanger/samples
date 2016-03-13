package ro.stancalau.springfx.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import org.slf4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import ro.stancalau.springfx.config.AppConfig;
import ro.stancalau.springfx.model.LanguageModel;

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
