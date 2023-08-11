package larry.ramirez.reactivewebbeta;

import larry.ramirez.usecase.beta.bringallposts.BringAllPostsUseCase;
import larry.ramirez.usecase.beta.bringpostbyid.BringPostByIdUseCase;
import larry.ramirez.usecase.generic.gateways.model.PostViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final BringAllPostsUseCase bringAllPostsUseCase;
    private final BringPostByIdUseCase bringPostByIdUseCase;


    public Mono<ServerResponse> listenGETBringAllPostsUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(bringAllPostsUseCase
                        .get(), PostViewModel.class));
    }

    public Mono<ServerResponse> listenGETBringPostByIdUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(bringPostByIdUseCase
                                .apply(serverRequest.pathVariable("id")),
                        PostViewModel.class));
    }
}
