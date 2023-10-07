package be.mvannuff.compta.poseidon.app.fx.controller.module;

import be.mvannuff.compta.poseidon.app.fx.controller.AbstractController;
import be.mvannuff.compta.poseidon.app.fx.lib.router.Routable;
import be.mvannuff.compta.poseidon.app.fx.lib.router.RouterView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class ModuleSelection extends AbstractController implements Routable {
    private final Resource fxmlFile = new ClassPathResource("view/ModueSelection.fxml");
    private final Stage stage;

    @Override
    public RouterView identify() {
        return RouterView.MODULE_SELECTION;
    }

    @Override
    public void onRoute() {
       var root =  super.load();
        stage.setScene(root);
    }
}
