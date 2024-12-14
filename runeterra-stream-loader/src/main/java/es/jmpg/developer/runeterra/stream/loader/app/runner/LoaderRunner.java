package es.jmpg.developer.runeterra.stream.loader.app.runner;

import es.jmpg.developer.runeterra.stream.loader.app.loader.LoaderComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoaderRunner {

    private final LoaderComponent loaderComponent;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        loaderComponent.run();
    }
}
