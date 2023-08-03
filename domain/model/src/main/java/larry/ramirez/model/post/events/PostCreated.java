package larry.ramirez.model.post.events;

import larry.ramirez.model.post.Comment;
import larry.ramirez.model.post.generic.DomainEvent;
import larry.ramirez.model.post.values.comment.CommentId;
import larry.ramirez.model.post.values.commons.Author;
import larry.ramirez.model.post.values.post.Title;

public class PostCreated extends DomainEvent {

    private final Title title;
    private final Author postAuthor;


    public PostCreated(Title title, Author postAuthor) {
        super("larry.ramirez.PostCreated");
        this.title = title;
        this.postAuthor = postAuthor;
    }


    public Title getTitle() {
        return title;
    }

    public Author getPostAuthor() {
        return postAuthor;
    }
}
