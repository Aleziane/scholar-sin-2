package be.mvannuff.compta.poseidon.app.fx.config;

import javafx.fxml.FXMLLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@RequiredArgsConstructor
public class FxmlLoaderConfig {

    private final ApplicationContext applicationContext;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) //not sure FXMLLoader is thread safe
    public FXMLLoader fxmlLoader(){
        var loader = new FXMLLoader();
        loader.setControllerFactory(applicationContext::getBean);
        return loader;
    }

}
