package larry.ramirez.api;

import larry.ramirez.model.post.commands.AddCommentCommand;
import larry.ramirez.model.post.commands.CreatePostCommand;
import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.usecase.addcomment.AddCommentUseCase;
import larry.ramirez.usecase.createpost.CreatePostUseCase;
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

    private  final CreatePostUseCase createPostUseCase;
    private  final AddCommentUseCase addCommentUseCase;


    public Mono<ServerResponse> listenPOSTCreatePostUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(createPostUseCase
                        .apply(serverRequest.bodyToMono(CreatePostCommand.class)),
                        DomainEvent.class));
    }

    public Mono<ServerResponse> listenPOSTAddCommentUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(addCommentUseCase
                                .apply(serverRequest.bodyToMono(AddCommentCommand.class)),
                        DomainEvent.class));
    }
}
