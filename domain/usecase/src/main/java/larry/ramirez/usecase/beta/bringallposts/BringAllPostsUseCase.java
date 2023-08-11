package larry.ramirez.usecase.beta.bringallposts;

import larry.ramirez.usecase.generic.gateways.DomainViewRepository;
import larry.ramirez.usecase.generic.gateways.model.PostViewModel;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

public class BringAllPostsUseCase implements Supplier<Flux<PostViewModel>> {

    private final DomainViewRepository repository;


    public BringAllPostsUseCase(DomainViewRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<PostViewModel> get() {
        return repository.findAllPosts();
    }
}
