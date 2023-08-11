package larry.ramirez.betamqlistener;

import larry.ramirez.betamqlistener.data.Notification;
import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.serializer.JSONMapper;
import larry.ramirez.serializer.JSONMapperImpl;
import larry.ramirez.usecase.beta.updateview.UpdateViewUseCase;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.logging.Logger;

@AllArgsConstructor
public class RabbitMQMessageListener {

    private final UpdateViewUseCase useCase;
    private final JSONMapper eventSerializer;
    public static final String GENERAL_QUEUE = "sca.events.general.queue";
    private final Logger logger = Logger.getLogger("BETAMessageListener");
    private final JSONMapper mapper = new JSONMapperImpl();

    @RabbitListener(queues = GENERAL_QUEUE)
    public void process(String message) throws ClassNotFoundException {
        Notification notification = Notification.from(message);
        DomainEvent event = (DomainEvent) eventSerializer
                .readFromJson(notification.getBody(), Class.forName(notification.getType()));
        useCase.accept(event);
    }
}
