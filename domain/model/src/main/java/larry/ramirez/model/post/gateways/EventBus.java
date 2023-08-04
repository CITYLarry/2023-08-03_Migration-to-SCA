package larry.ramirez.model.post.gateways;

import larry.ramirez.model.post.generic.DomainEvent;

public interface EventBus {

    void publish(DomainEvent event);
    void publishError(Throwable errorEvent);
}
