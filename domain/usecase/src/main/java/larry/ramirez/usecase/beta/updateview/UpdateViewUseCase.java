package larry.ramirez.usecase.beta.updateview;

import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.usecase.beta.ViewUpdater;
import larry.ramirez.usecase.generic.gateways.EventBus;

import java.util.function.Consumer;

public class UpdateViewUseCase implements Consumer<DomainEvent> {

    private final EventBus bus;
    private final ViewUpdater updater;


    public UpdateViewUseCase(EventBus bus, ViewUpdater updater) {
        this.bus = bus;
        this.updater = updater;
    }


    @Override
    public void accept(DomainEvent event) {
        updater.applyEvent(event);
    }
}
