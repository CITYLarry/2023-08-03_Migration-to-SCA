package larry.ramirez.usecase.createpost;

import larry.ramirez.model.post.Post;
import larry.ramirez.model.post.commands.CreatePostCommand;
import larry.ramirez.model.post.gateways.DomainEventRepository;
import larry.ramirez.model.post.gateways.EventBus;
import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.model.post.values.commons.Author;
import larry.ramirez.model.post.values.post.PostId;
import larry.ramirez.model.post.values.post.Title;
import larry.ramirez.usecase.generic.UseCaseForCommand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreatePostUseCase extends UseCaseForCommand<CreatePostCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;


    public CreatePostUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<CreatePostCommand> commandMono) {

        return commandMono.flatMapIterable(command -> {
            Post post = new Post(
                    PostId.of(command.getPostId()),
                    new Title(command.getTitle()),
                    new Author(command.getPostAuthor())
            );
            return post.getUncommittedChanges();
        }).map(event -> {
            bus.publish(event);
            return event;
        }).flatMap(repository::saveEvent);
    }
}
