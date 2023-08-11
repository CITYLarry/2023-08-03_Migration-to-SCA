package larry.ramirez.usecase.beta.bringpostbyid;

import larry.ramirez.usecase.generic.gateways.DomainViewRepository;
import larry.ramirez.usecase.generic.gateways.model.PostViewModel;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class BringPostByIdUseCase implements Function<String, Mono<PostViewModel>> {

    private final DomainViewRepository repository;


    public BringPostByIdUseCase(DomainViewRepository repository) {
        this.repository = repository;
    }


    @Override
    public Mono<PostViewModel> apply(String id) {
        return repository.findByAggregateId(id);
    }
}
