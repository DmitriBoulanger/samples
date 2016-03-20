package de.dbo.samples.javafx.spring2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.dbo.samples.javafx.spring2.ui.MainController;
import de.dbo.samples.javafx.spring2.ui.MainView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 27.08.15
 * Time: 11:04
 *
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 * @author http://mruslan.com
 */
@Configuration
public class SpringBootableApplicationConfiguration {

    @Bean(name = "mainView")
    public MainView getMainView() throws IOException {
        return loadView("MainView.fxml");
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его сделать произвести все необходимые инъекции.
     */
    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }

    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    protected MainView loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new MainView(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

}
