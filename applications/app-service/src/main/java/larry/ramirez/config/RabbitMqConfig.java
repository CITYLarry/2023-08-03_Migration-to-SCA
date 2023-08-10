package larry.ramirez.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE = "SCA_PostsAndComments_exchange";
    public static final String EVENTS_QUEUE = "sca.events.queue";
    public static final String ROUTING_KEY = "sca.events.routing.key";


    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate template) {
        RabbitAdmin admin = new RabbitAdmin(template);
        admin.declareExchange(new TopicExchange(EXCHANGE));
        return admin;
    }


    @Bean
    public TopicExchange eventsExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue eventsQueue() {
        return new Queue(EVENTS_QUEUE);
    }


    @Bean
    public Binding eventsBinding() {
        return BindingBuilder
                .bind(this.eventsQueue())
                .to(this.eventsExchange())
                .with(ROUTING_KEY);
    }
}
