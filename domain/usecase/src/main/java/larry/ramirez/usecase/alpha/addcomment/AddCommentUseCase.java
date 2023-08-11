package larry.ramirez.usecase.alpha.addcomment;

import larry.ramirez.model.post.Post;
import larry.ramirez.model.post.commands.AddCommentCommand;
import larry.ramirez.usecase.generic.gateways.DomainEventRepository;
import larry.ramirez.usecase.generic.gateways.EventBus;
import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.model.post.values.comment.CommentId;
import larry.ramirez.model.post.values.comment.Content;
import larry.ramirez.model.post.values.commons.Author;
import larry.ramirez.model.post.values.post.PostId;
import larry.ramirez.usecase.generic.UseCaseForCommand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AddCommentUseCase extends UseCaseForCommand<AddCommentCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;


    public AddCommentUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<AddCommentCommand> commandMono) {
        return commandMono.flatMapMany(command -> repository.findById(command.getPostId())
                .collectList()
                .flatMapIterable(events -> {
                    Post post = Post.from(PostId.of(command.getPostId()), events);
                    post.addAComment(
                            CommentId.of(command.getCommentId()),
                            new Author(command.getCommentAuthor()),
                            new Content(command.getContent())
                    );
                    return post.getUncommittedChanges();
                }).map(event -> {
                    bus.publish(event);
                    return event;
                }).flatMap(repository::saveEvent)
        );
    }
}
