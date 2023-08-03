package larry.ramirez.model.post.generic;

import java.util.Objects;
import java.util.UUID;

public class Identity implements ValueObject<String>{

    private final String uuid;


    public Identity() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Identity (String uuid) {
        this.uuid = uuid;
    }


    public String value() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return Objects.equals(uuid, identity.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
