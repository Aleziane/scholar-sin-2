package be.mvannuff.compta.poseidon.app.fx;

import be.mvannuff.compta.poseidon.app.FxEntryPoint;
import be.mvannuff.compta.poseidon.app.fx.controller.AutoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class StageListener implements ApplicationListener<FxEntryPoint.StageReadyEvent> {

    private final AutoLoader homeController;

    @Override
    public void onApplicationEvent(FxEntryPoint.StageReadyEvent event) {
        try {
            homeController.autoLoad();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
}
