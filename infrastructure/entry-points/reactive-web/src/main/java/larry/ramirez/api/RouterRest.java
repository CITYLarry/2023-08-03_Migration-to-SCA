package larry.ramirez.api;

import larry.ramirez.model.post.commands.CreatePostCommand;
import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.usecase.createpost.CreatePostUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(
                POST("/api/v1/CreatePost")
                        .and(accept(MediaType.APPLICATION_JSON)),
                handler::listenPOSTCreatePostUseCase
        ).andRoute(
                POST("/api/v1/AddComment")
                        .and(accept(MediaType.APPLICATION_JSON)),
                handler::listenPOSTAddCommentUseCase
        );
    }
}
