package larry.ramirez.usecase.generic.gateways;

import larry.ramirez.model.post.generic.DomainEvent;

public interface EventBus<T> {

    void publish(DomainEvent event);
    void publishGeneric(T object, String routingKey);
    void publishError(Throwable errorEvent);
}
