package larry.ramirez.usecase.generic.gateways;

import larry.ramirez.model.post.generic.DomainEvent;

public interface EventBus {

    void publish(DomainEvent event);
    void publishError(Throwable errorEvent);
}
