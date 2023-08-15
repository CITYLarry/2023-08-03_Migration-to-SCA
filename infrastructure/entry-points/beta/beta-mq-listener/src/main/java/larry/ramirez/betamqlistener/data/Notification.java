package larry.ramirez.betamqlistener.data;

import larry.ramirez.serializer.JSONMapperImpl;

import java.time.Instant;

public class Notification {

    private final String type;
    private final String body;
    private final Instant when;

    private Notification() {
        this(null, null);
    }

    public Notification(String type, String body) {
        this.type = type;
        this.body = body;
        this.when = Instant.now();
    }

    public String getType() {
        return type;
    }

    public String getBody() {
        return body;
    }

    public Instant getWhen() {
        return when;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "type='" + type + '\'' +
                ", body='" + body + '\'' +
                ", when=" + when +
                '}';
    }

    public Notification deserialize(String aSerialization) {
        return (Notification) new JSONMapperImpl().readFromJson(aSerialization, Notification.class);
    }

    public String serialize() {
        return new JSONMapperImpl().writeToJson(this);
    }

    public static Notification from(String aNotification) {
        return new Notification().deserialize(aNotification);
    }
}
