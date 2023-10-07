package be.mvannuff.compta.poseidon.app.fx.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Getter
@RequiredArgsConstructor
public abstract class AbstractController {

    private FXMLLoader fxmlLoader;

    @Autowired
    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        try {
            fxmlLoader.setLocation(getFxmlFile().getURL());
            this.fxmlLoader = fxmlLoader;
        } catch (IOException e) {
            throw new RuntimeException("View not found", e);
        }
    }

    @SneakyThrows
    public Scene load(){
        Parent parent =  fxmlLoader.load();
        //todo deal with size
        return new Scene(parent);
    }

    public abstract Resource getFxmlFile();

}
