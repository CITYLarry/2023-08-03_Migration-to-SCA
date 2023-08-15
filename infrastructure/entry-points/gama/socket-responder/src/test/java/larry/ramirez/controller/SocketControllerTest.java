package larry.ramirez.controller;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class SocketControllerTest {

    private final SocketController socketController = new SocketController();

    @Test
        void testRequestResponse(){
        Mono<Object> requestResponse = socketController.getRequestResponse("object");

        StepVerifier.create(requestResponse)
        .verifyComplete();
    }

    @Test
    void testRequestStream(){
        Flux<Object> requestStream = socketController.getRequestStream();

        StepVerifier.create(requestStream)
        .verifyComplete();
    }

    @Test
    void testFireforget(){
        Mono<Void> requetsFireForget = socketController.getRequetsFireForget("object");

        StepVerifier.create(requetsFireForget)
        .verifyComplete();
    }

    @Test
    void testChannel(){
         Flux<Object> channel = socketController.getChannel(Flux.just("object"));

         StepVerifier.create(channel)
         .verifyComplete();
    }
}