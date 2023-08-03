package larry.ramirez.model.post.values.post;

import larry.ramirez.model.post.generic.ValueObject;

import java.util.Objects;

public class Title implements ValueObject<String> {

    private String title;


    public Title( String title) {
        if(title.length() <= 9){
            throw new IllegalArgumentException("Title must have a least 10 characters");
        }
        this.title = Objects.requireNonNull(title);
    }


    @Override
    public String value() {
        return title;
    }
}
