package larry.ramirez.events;

import larry.ramirez.events.data.ErrorEvent;
import larry.ramirez.events.data.Notification;
import larry.ramirez.model.post.gateways.EventBus;
import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.serializer.JSONMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventBus implements EventBus {

    public static final String EXCHANGE = "SCA_PostsAndComments_exchange";
    public static final String ROUTING_KEY = "sca.events.routing.key";
    private final RabbitTemplate template;
    private final JSONMapper eventSerializer;

    public RabbitMqEventBus(RabbitTemplate template, JSONMapper eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public void publish(DomainEvent event) {
        template.convertAndSend(
                EXCHANGE,
                ROUTING_KEY,
                new Notification(
                        event.getClass().getTypeName(),
                        eventSerializer.writeToJson(event)
                )
                        .serialize()
                        .getBytes()
        );
    }

    @Override
    public void publishError(Throwable errorEvent) {
        ErrorEvent event = new ErrorEvent(errorEvent.getClass().getTypeName(), errorEvent.getMessage());
        template.convertAndSend(
                EXCHANGE,
                ROUTING_KEY,
                new Notification(
                        event.getClass().getTypeName(),
                        eventSerializer.writeToJson(event)
                )
                        .serialize()
                        .getBytes()
        );
    }
}
