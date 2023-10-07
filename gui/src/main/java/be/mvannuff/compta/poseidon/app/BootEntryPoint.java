package be.mvannuff.compta.poseidon.app;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootEntryPoint {

    public static void main(String[] args) {
        Application.launch(FxEntryPoint.class, args);
    }

}
