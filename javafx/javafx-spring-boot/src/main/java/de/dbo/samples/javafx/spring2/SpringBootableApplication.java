package de.dbo.samples.javafx.spring2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.dbo.samples.javafx.spring2.ui.MainView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class SpringBootableApplication extends Application  {

    @Value("${ui.title:JavaFX Spring-boot Application}") 
    private String windowTitle;

    @Qualifier("mainView")
    @Autowired
    private MainView view;

   @Override
    public final void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }
   
   @Override
   public final void init() throws Exception {
       context = SpringApplication.run(getClass(), savedArgs);
       context.getAutowireCapableBeanFactory().autowireBean(this);
   }
    
    private static String[] savedArgs;

    protected ConfigurableApplicationContext context;



    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    public static void launchApp(Class<? extends Application> appClass, String[] args) {
        savedArgs = args;
        Application.launch(appClass, args);
    }

    public static void main(String[] args) {
	savedArgs = args;
        Application.launch(SpringBootableApplication.class, args);
    }

}
