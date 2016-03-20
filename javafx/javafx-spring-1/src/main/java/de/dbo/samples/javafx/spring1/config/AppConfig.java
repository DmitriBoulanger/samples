package de.dbo.samples.javafx.spring1.config;

import de.dbo.samples.javafx.spring1.control.LanguageController;
import de.dbo.samples.javafx.spring1.gui.ScreensConfig;
import de.dbo.samples.javafx.spring1.model.LanguageModel;
import de.dbo.samples.javafx.spring1.model.MessageModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(ScreensConfig.class)
public class AppConfig {

	
	@Bean
	LanguageModel languageModel() {
		return new LanguageModel();
	}
	
	@Bean
	LanguageController languageController() {
		return new LanguageController(languageModel());
	}

	@Bean
	MessageModel messageModel() {
		return new MessageModel();
	}
	
	
}
