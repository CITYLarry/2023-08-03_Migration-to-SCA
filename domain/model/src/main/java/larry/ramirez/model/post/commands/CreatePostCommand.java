package larry.ramirez.model.post.commands;

import larry.ramirez.model.post.generic.Command;

public class CreatePostCommand extends Command {

    private String postId;
    private String postAuthor;
    private String title;


    public CreatePostCommand() {
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
