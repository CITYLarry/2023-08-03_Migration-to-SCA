package larry.ramirez.model.post.generic;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public abstract class Command implements Serializable {

    public final String uuid;
    public final Instant when;


    public Command() {
        this.uuid = UUID.randomUUID().toString();
        this.when = Instant.now();
    }


    public String uuid() {
        return uuid;
    }

    public Instant when() {
        return when;
    }
}
