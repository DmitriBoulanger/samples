package de.dbo.samples.javafx.spring2;

import static de.dbo.tools.utils.print.Profiler.elapsed;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.dbo.samples.javafx.spring2.ui.View;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

@Lazy
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringBootableApplication extends Application  {
    private static final Logger log = LoggerFactory.getLogger(SpringBootableApplication.class);
    
    private static String[] maindArgs;
    
    public static void main(String[] args) {
	maindArgs = args;
        Application.launch(SpringBootableApplication.class, args);
    }

    /* gets its value from application.properties */
    @Value("${ui.title:JavaFX Application}") 
    private String windowTitle;

    @Qualifier("View")
    @Autowired
    private View view;
    
    /* Spring-context of the application */
    protected ConfigurableApplicationContext context;

   /**
    * the method is called first before starting UI-stream in FX.
    * This method creates and initializes Spring-context.
    * The super-implementation is void
    */
   @Override
   public final void init() throws Exception {
       final long start = System.currentTimeMillis();
       log.info("initializing Spring-contex .....");
       context = SpringApplication.run(SpringBootableApplication.class, maindArgs);
       context.getAutowireCapableBeanFactory().autowireBean(this);
       log.info("initializing Spring-contex done. " + elapsed(start));
   }
   
   /**
    * called from FX UI-stream.
    * UI-activities should be done here, e.g. pop-up open
    */
   @Override
   public final void start(Stage stage) throws Exception {
       final long start = System.currentTimeMillis();
       log.info("starting FX UI-stream .....");
       stage.setTitle(windowTitle);
       stage.setScene(new Scene(view.getView()));
       stage.setResizable(true);
       stage.centerOnScreen();
       stage.show();
       log.info("starting FX UI-stream done." + elapsed(start));
   }
    
   /**
    * the method closes Spring-context
    * The super-implementation is void
    */
    @Override
    public void stop() throws Exception {
	log.info("closing FX UI ...");
        super.stop();
        log.info("closing Spring-contex ...");
        context.close();
    }
}
