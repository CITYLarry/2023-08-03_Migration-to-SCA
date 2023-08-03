package larry.ramirez.model.post;

import larry.ramirez.model.post.generic.Entity;
import larry.ramirez.model.post.values.comment.CommentId;
import larry.ramirez.model.post.values.comment.Content;
import larry.ramirez.model.post.values.commons.Author;

public class Comment extends Entity<CommentId> {

    private Author author;
    private Content content;


    public Comment(CommentId entityId, Author author, Content content) {
        super(entityId);
        this.author = author;
        this.content = content;
    }


    public Author author() {
        return author;
    }

    public Content content() {
        return content;
    }
}
