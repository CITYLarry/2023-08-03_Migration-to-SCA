package larry.ramirez.model.post.values.comment;

import larry.ramirez.model.post.generic.ValueObject;

public class Content implements ValueObject<String> {

    private String content;


    public Content(String content) {
        this.content = content;
    }


    @Override
    public String value() {
        return content;
    }
}
