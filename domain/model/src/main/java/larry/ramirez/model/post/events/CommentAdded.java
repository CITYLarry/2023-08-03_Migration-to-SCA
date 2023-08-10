package larry.ramirez.model.post.events;

import larry.ramirez.model.post.generic.DomainEvent;

public class CommentAdded extends DomainEvent {

    private String commentId;
    private String commentAuthor;
    private String content;

    public CommentAdded() {
        super("larry.ramirez.model.post.events.CommentAdded");
    }

    public CommentAdded(String commentId, String commentAuthor, String content) {
        super("larry.ramirez.model.post.events.CommentAdded");
        this.commentId = commentId;
        this.commentAuthor = commentAuthor;
        this.content = content;
    }


    public String getCommentId() {
        return commentId;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public String getContent() {
        return content;
    }
}
