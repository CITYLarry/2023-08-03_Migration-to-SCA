package larry.ramirez.model.post.commands;

import larry.ramirez.model.post.generic.Command;

public class AddCommentCommand extends Command {

    private String postId;
    private String commentId;
    private String commentAuthor;
    private String content;


    public AddCommentCommand() {
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
