package larry.ramirez.reactivewebbeta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(
                GET("/api/v1/BringAllPosts"),
                handler::listenGETBringAllPostsUseCase
        ).andRoute(
                GET("/api/v1/BringPost/{id}"),
                handler::listenGETBringPostByIdUseCase
        );
    }
}
