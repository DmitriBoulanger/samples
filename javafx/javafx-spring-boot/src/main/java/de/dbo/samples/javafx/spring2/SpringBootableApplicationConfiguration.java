package de.dbo.samples.javafx.spring2;


import de.dbo.samples.javafx.spring2.ui.Controller;
import de.dbo.samples.javafx.spring2.ui.View;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javafx.fxml.FXMLLoader;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

@Configuration
public class SpringBootableApplicationConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SpringBootableApplicationConfiguration.class);
    

    @Bean(name = "View")
    public View getView() throws IOException {
        return loadView("View.fxml");
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его сделать произвести все необходимые инъекции.
     */
    @Bean
    public Controller getMainController() throws IOException {
        return getView().getController();
    }
    
    /**
     * Standard way to load FXML views. It creates a controller
     * performs all injections and after that calls controler initialization
     */
    private static View loadView(String url) throws IOException {
	View view = null;
        InputStream fxmlStream = null;
        try {
            fxmlStream = ClassLoader.getSystemClassLoader().getResourceAsStream(url);
            final FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            view =  new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
        log.debug("view loaded.");
        return view;
    }

 

}
