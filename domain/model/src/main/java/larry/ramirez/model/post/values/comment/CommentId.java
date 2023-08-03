package larry.ramirez.model.post.values.comment;

import larry.ramirez.model.post.generic.Identity;

public class CommentId extends Identity {

    public CommentId() {
    }

    private CommentId(String uuid) {
        super(uuid);
    }


    public static CommentId of(String uuid){
        return new CommentId(uuid);
    }
}
