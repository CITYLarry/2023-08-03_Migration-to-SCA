package larry.ramirez.usecase.beta;

import larry.ramirez.model.post.events.CommentAdded;
import larry.ramirez.model.post.events.PostCreated;
import larry.ramirez.model.post.generic.DomainUpdater;
import larry.ramirez.usecase.generic.gateways.DomainViewRepository;
import larry.ramirez.usecase.generic.gateways.EventBus;
import larry.ramirez.usecase.generic.gateways.model.CommentViewModel;
import larry.ramirez.usecase.generic.gateways.model.PostViewModel;

import java.util.ArrayList;

public class ViewUpdater extends DomainUpdater {

    private final DomainViewRepository repository;
    private final EventBus bus;


    public ViewUpdater(DomainViewRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;

        listen((PostCreated event) -> {
            PostViewModel post = new PostViewModel(event.aggregateRootId(), event.getPostAuthor(), event.getTitle(), new ArrayList<>());
            bus.publishGeneric(post, event.type);
            repository.saveNewPost(post).subscribe();
        });
        listen((CommentAdded event) -> {
            CommentViewModel comment = new CommentViewModel(event.getCommentId(), event.aggregateRootId(), event.getCommentAuthor(), event.getContent());
            bus.publishGeneric(comment, event.type);
            repository.addCommentToPost(comment).subscribe();
        });
    }
}
