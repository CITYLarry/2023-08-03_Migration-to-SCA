package larry.ramirez.model.post.values.commons;

import larry.ramirez.model.post.generic.ValueObject;

public class Author implements ValueObject<String> {

    private String author;

    public Author(String author) {
        this.author = author;
    }


    @Override
    public String value() {
        return author;
    }
}
