package larry.ramirez.model.post.events;

import larry.ramirez.model.post.generic.DomainEvent;

public class PostCreated extends DomainEvent {

    private String title;
    private String postAuthor;

    public PostCreated() {
        super("larry.ramirez.model.post.events.PostCreated");
    }

    public PostCreated(String title, String postAuthor) {
        super("larry.ramirez.model.post.events.PostCreated");
        this.title = title;
        this.postAuthor = postAuthor;
    }


    public String getTitle() {
        return title;
    }

    public String getPostAuthor() {
        return postAuthor;
    }
}
