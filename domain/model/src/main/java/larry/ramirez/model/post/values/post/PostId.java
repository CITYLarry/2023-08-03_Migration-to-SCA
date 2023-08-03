package larry.ramirez.model.post.values.post;

import larry.ramirez.model.post.generic.Identity;

public class PostId extends Identity {


    public PostId() {
    }

    private PostId(String uuid) {
        super(uuid);
    }


    public static PostId of(String uuid){
        return new PostId(uuid);
    }
}
