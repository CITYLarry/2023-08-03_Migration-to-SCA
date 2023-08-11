package larry.ramirez.asyncviewbus;

import larry.ramirez.serializer.JSONMapper;
import larry.ramirez.usecase.generic.gateways.ViewBus;
import larry.ramirez.usecase.generic.gateways.model.CommentViewModel;
import larry.ramirez.usecase.generic.gateways.model.PostViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMQViewBus implements ViewBus {

    public static final String EXCHANGE = "SCA_PostsAndComments_exchange";
    public static final String POST_ROUTING_KEY = "sca.events.routing.key.postcreated";
    public static final String COMMENT_ROUTING_KEY = "sca.events.routing.key.commentadded";
    private final RabbitTemplate template;
    private final JSONMapper eventSerializer;

    public RabbitMQViewBus(RabbitTemplate template, JSONMapper eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }


    @Override
    public void publishPost(PostViewModel view) {
            template.convertAndSend(
                    EXCHANGE,
                    POST_ROUTING_KEY,
                    eventSerializer.writeToJson(view).getBytes()
            );
    }

    @Override
    public void publishComment(CommentViewModel view) {
        template.convertAndSend(
                EXCHANGE,
                COMMENT_ROUTING_KEY,
                eventSerializer.writeToJson(view).getBytes()
        );
    }
}
