package larry.ramirez.model.post.events;

import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.model.post.values.comment.CommentId;
import larry.ramirez.model.post.values.comment.Content;
import larry.ramirez.model.post.values.commons.Author;

public class CommentAdded extends DomainEvent {

    private final CommentId commentId;
    private final Author commentAuthor;
    private final Content content;


    public CommentAdded(CommentId commentId, Author commentAuthor, Content content) {
        super("larry.ramirez.CommentAdded");
        this.commentId = commentId;
        this.commentAuthor = commentAuthor;
        this.content = content;
    }


    public CommentId getCommentId() {
        return commentId;
    }

    public Author getCommentAuthor() {
        return commentAuthor;
    }

    public Content getContent() {
        return content;
    }
}
