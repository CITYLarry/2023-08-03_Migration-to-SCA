package larry.ramirez.usecase.beta.updateview;

import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.usecase.beta.ViewUpdater;

import java.util.function.Consumer;

public class UpdateViewUseCase implements Consumer<DomainEvent> {

    private final ViewUpdater updater;


    public UpdateViewUseCase(ViewUpdater updater) {
        this.updater = updater;
    }


    @Override
    public void accept(DomainEvent event) {
        updater.applyEvent(event);
    }
}
