package de.dbo.samples.spring.di.configuration;

import de.dbo.samples.spring.di.services.EmailService;
import de.dbo.samples.spring.di.services.MessageService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = { "de.dbo.samples.spring.di.consumer" })
public class DIConfiguration {

    @Bean
    public MessageService getMessageService() {
	return new EmailService();
    }
}
