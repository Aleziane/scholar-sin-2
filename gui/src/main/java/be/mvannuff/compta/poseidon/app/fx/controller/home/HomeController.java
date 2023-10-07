package be.mvannuff.compta.poseidon.app.fx.controller.home;

import be.mvannuff.compta.poseidon.app.fx.controller.AbstractController;
import be.mvannuff.compta.poseidon.app.fx.controller.AutoLoader;
import be.mvannuff.compta.poseidon.app.fx.lib.router.Routable;
import be.mvannuff.compta.poseidon.app.fx.lib.router.RouterView;
import be.mvannuff.compta.poseidon.app.fx.service.Router;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Getter
@RequiredArgsConstructor
public class HomeController extends AbstractController implements AutoLoader, Routable {

    private final Stage stage;
    private final Resource fxmlFile = new ClassPathResource("view/Home.fxml");
    private final Resource parrot = new ClassPathResource("img/parrot.png");
    private final Router router;

//    @FXML
//    private Button test1;
//
//    @FXML
//    private Button test2;
//    @FXML
//    private Button test11;

    @FXML
    private Label label;

    @FXML
    private HBox hbox;

    @Override
    public void autoLoad() throws IOException {
        var scene = super.load();
        //Creating an image
        Image image = new Image(parrot.getInputStream());
        //Setting the image view
        ImageView imageView = new ImageView(image);
        var x = new SimpleDoubleProperty(700);
        var y = new SimpleDoubleProperty(100);
        imageView.xProperty().bindBidirectional(x);
        imageView.yProperty().bindBidirectional(y);
        var hp = new SimpleDoubleProperty(455);
        var wp = new SimpleDoubleProperty(500);
        imageView.fitHeightProperty().bindBidirectional(hp);
        imageView.fitWidthProperty().bindBidirectional(wp);
        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);
        //Creating a Group object
        Group root = new Group(imageView);
        hbox.getChildren().add(root);
        stage.setScene(scene);

//        test1.setOnMouseClicked(e -> router.route(RouterView.MODULE_SELECTION));

        //only this one should show, other one will only switch between scene
        stage.show();
    }

    @Override
    public RouterView identify() {
        return RouterView.HOME;
    }

    @Override
    @SneakyThrows
    public void onRoute() {
        this.autoLoad();
    }
}
