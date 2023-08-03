package larry.ramirez.model.post;

import larry.ramirez.model.post.generic.AggregateRoot;
import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.model.post.values.comment.CommentId;
import larry.ramirez.model.post.values.comment.Content;
import larry.ramirez.model.post.values.commons.Author;
import larry.ramirez.model.post.values.post.PostId;
import larry.ramirez.model.post.values.post.Title;

import java.util.List;
import java.util.Objects;

public class Post extends AggregateRoot<PostId> {

    protected Title title;
    protected Author author;
    protected List<Comment> commentList;


    public Post(PostId entityId, Title title, Author author, CommentId commentId, Author commentAuthor, Content content) {
        super(entityId);
        // TODO: PostChange logic
    }

    private Post(PostId entityId) {
        super(entityId);
        // TODO: PostChange logic
    }


    public static Post from(PostId entityId, List<DomainEvent> events) {
        Post post = new Post(entityId);
        events.forEach(post::applyEvent);
        return post;
    }

    public void addAComment(CommentId entityId, Author author, Content content) {
        Objects.requireNonNull(entityId, "Entity id cannot be null");
        Objects.requireNonNull(author, "Author cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        // TODO: append change to event commentAdded
    }
}
