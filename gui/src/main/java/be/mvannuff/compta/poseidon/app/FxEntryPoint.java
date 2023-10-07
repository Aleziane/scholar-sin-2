package be.mvannuff.compta.poseidon.app;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class FxEntryPoint extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setMaximized(true);

        stage.initStyle(StageStyle.UNDECORATED);
        ApplicationContextInitializer<GenericApplicationContext> initializer =
                ac -> {
                    ac.registerBean(Application.class, () -> FxEntryPoint.this);
                    ac.registerBean(Parameters.class, this::getParameters);
                    ac.registerBean(HostServices.class, this::getHostServices);
                    ac.registerBean(Stage.class, ()-> stage);
                };

        this.context = new SpringApplicationBuilder()
                .sources(BootEntryPoint.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));

        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

    public static class StageReadyEvent extends ApplicationEvent {

        public Stage getStage() {
            return Stage.class.cast(getSource());
        }

        public StageReadyEvent(Stage stage) {
            super(stage);
        }
    }
}

