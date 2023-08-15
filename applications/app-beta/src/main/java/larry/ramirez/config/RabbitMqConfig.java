package larry.ramirez.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE = "SCA_PostsAndComments_exchange";
    public static final String POST_CREATED_QUEUE = "sca.events.post.created.queue";
    public static final String COMMENT_ADDED_QUEUE = "sca.events.comment.added.queue";
    public static final String GENERAL_QUEUE = "sca.events.queue";

    public static final String POST_CREATED_ROUTING_KEY = "sca.events.post.created.routing.key";
    public static final String COMMENT_ADDED_ROUTING_KEY = "sca.events.comment.added.routing.key";
    public static final String GENERAL_ROUTING_KEY = "sca.events.routing.key";


    @Bean
    public TopicExchange eventsExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue postCreatedQueue() {
        return new Queue(POST_CREATED_QUEUE);
    }

    @Bean
    public Queue commentAddedQueue() {
        return new Queue(COMMENT_ADDED_QUEUE);
    }

    @Bean
    public Queue generalQueue() {
        return new Queue(GENERAL_QUEUE);
    }


    @Bean
    public Binding postCreatedBinding() {
        return BindingBuilder
                .bind(this.postCreatedQueue())
                .to(this.eventsExchange())
                .with(POST_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding commentAddedBinding() {
        return BindingBuilder
                .bind(this.commentAddedQueue())
                .to(this.eventsExchange())
                .with(COMMENT_ADDED_ROUTING_KEY);
    }

    @Bean
    public Binding generalBinding() {
        return BindingBuilder
                .bind(this.generalQueue())
                .to(this.eventsExchange())
                .with(GENERAL_ROUTING_KEY);
    }
}
