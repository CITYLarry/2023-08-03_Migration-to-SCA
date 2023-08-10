package larry.ramirez.model.post;

import larry.ramirez.model.post.events.CommentAdded;
import larry.ramirez.model.post.events.PostCreated;
import larry.ramirez.model.post.generic.EventChange;
import larry.ramirez.model.post.values.comment.CommentId;
import larry.ramirez.model.post.values.comment.Content;
import larry.ramirez.model.post.values.commons.Author;
import larry.ramirez.model.post.values.post.Title;

import java.util.ArrayList;

public class PostChange extends EventChange {

    public PostChange(Post post) {
        apply((PostCreated event) -> {
            post.title = new Title(event.getTitle());
            post.author = new Author(event.getPostAuthor());
            post.commentList = new ArrayList<>();
        });

        apply((CommentAdded event) -> {
            Comment comment = new Comment(
                    CommentId.of(event.getCommentId()),
                    new Author(event.getCommentAuthor()),
                    new Content(event.getContent()));
            post.commentList.add(comment);
        });
    }
}
