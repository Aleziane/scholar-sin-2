package be.mvannuff.compta.poseidon.app.fx.service;

import be.mvannuff.compta.poseidon.app.fx.lib.router.Routable;
import be.mvannuff.compta.poseidon.app.fx.lib.router.RouterView;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Router {

    private final ApplicationContext applicationContext;

    public void route(RouterView view) {
        //fetch routables dynamically to avoid circular dependencies at injection point.
        //this is a bit hacky
        var routables = applicationContext.getBeansOfType(Routable.class);
        routables.values()
                .stream()
                .filter(v -> v.identify() == view)
                .findFirst()
                .ifPresent(Routable::onRoute);
    }


}
