package larry.ramirez.model.post;

import larry.ramirez.model.post.events.CommentAdded;
import larry.ramirez.model.post.events.PostCreated;
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


    public Post(PostId postId, Title title, Author author) {
        super(postId);
        subscribe(new PostChange(this));
        appendChange(new PostCreated(title, author)).apply();
    }

    private Post(PostId postId) {
        super(postId);
        subscribe(new PostChange(this));
    }


    public static Post from(PostId postId, List<DomainEvent> events) {
        Post post = new Post(postId);
        events.forEach(post::applyEvent);
        return post;
    }

    public void addAComment(CommentId commentId, Author author, Content content) {
        Objects.requireNonNull(commentId, "Entity id cannot be null");
        Objects.requireNonNull(author, "Author cannot be null");
        Objects.requireNonNull(content, "Content cannot be null");
        appendChange(new CommentAdded(commentId, author, content));
    }
}
