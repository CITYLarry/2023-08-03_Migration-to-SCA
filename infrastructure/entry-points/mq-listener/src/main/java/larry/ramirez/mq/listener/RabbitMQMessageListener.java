package larry.ramirez.mq.listener;

import larry.ramirez.mq.listener.data.Notification;
import larry.ramirez.serializer.JSONMapper;
import larry.ramirez.serializer.JSONMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class RabbitMQMessageListener {

    public static final String EVENTS_QUEUE = "sca.events.queue";
    private final Logger logger = Logger.getLogger("RabbitMQMessageListener");
    private final JSONMapper mapper = new JSONMapperImpl();

    @RabbitListener(queues = EVENTS_QUEUE)
    public void process(String message) throws ClassNotFoundException {
        Notification notification = Notification.from(message);
        if (
                notification
                        .getType()
                        .equals("larry.ramirez.model.post.events.PostCreated")
        ) {
            logger.info(notification.toString());
            return;
        }
        logger.info("Notification: event not supported");
    }
}
