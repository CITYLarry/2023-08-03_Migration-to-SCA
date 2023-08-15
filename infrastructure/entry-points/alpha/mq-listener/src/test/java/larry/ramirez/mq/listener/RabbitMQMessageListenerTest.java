package larry.ramirez.mq.listener;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

public class RabbitMQMessageListenerTest {

    @Mock
    private String textMessage;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    void processTest() throws JMSException {
        when(textMessage).thenReturn("message");

        RabbitMQMessageListener rabbitMQMessageListener = new RabbitMQMessageListener();

        StepVerifier.create(rabbitMQMessageListener.process(textMessage)).verifyComplete();
    }*/
}
