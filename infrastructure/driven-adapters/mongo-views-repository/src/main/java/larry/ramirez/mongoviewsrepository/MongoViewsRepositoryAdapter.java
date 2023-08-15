package larry.ramirez.mongoviewsrepository;

import larry.ramirez.serializer.JSONMapper;
import larry.ramirez.usecase.generic.gateways.DomainViewRepository;
import larry.ramirez.usecase.generic.gateways.model.CommentViewModel;
import larry.ramirez.usecase.generic.gateways.model.PostViewModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class MongoViewsRepositoryAdapter implements DomainViewRepository {

    private final ReactiveMongoTemplate template;
    private final JSONMapper eventSerializer;

    public MongoViewsRepositoryAdapter(ReactiveMongoTemplate template, JSONMapper eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public Mono<PostViewModel> findByAggregateId(String aggregateId) {
        Query query = new Query(Criteria.where("aggregateId").is(aggregateId));
        return template.findOne(query, PostViewModel.class);
    }

    @Override
    public Flux<PostViewModel> findAllPosts() {
        return template.findAll(PostViewModel.class);
    }

    @Override
    public Mono<PostViewModel> saveNewPost(PostViewModel post) {
        return template.save(post);
    }

    @Override
    public Mono<PostViewModel> addCommentToPost(CommentViewModel comment) {
        Query query = new Query(Criteria.where("aggregateId").is(comment.getPostId()));
        Update  update = new Update();
        return template.findOne(query, PostViewModel.class)
                .flatMap(postViewModel -> {
                    List<CommentViewModel> comments = postViewModel.getComments();
                    comments.add(comment);
                    update.set("comments", comments);
                    return template.findAndModify(query, update , PostViewModel.class);
                });
    }
}
