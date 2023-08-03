package larry.ramirez.model.post.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}
