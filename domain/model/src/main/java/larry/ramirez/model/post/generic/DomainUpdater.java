package larry.ramirez.model.post.generic;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class DomainUpdater {

    protected Set<Consumer<? super DomainEvent>> listeners = new HashSet<>();


    public DomainUpdater() {
    }


    protected void listen(Consumer<? extends DomainEvent> changeEvent) {
        this.listeners.add((Consumer<? super DomainEvent>) changeEvent);
    }

    public final void applyEvent(DomainEvent event) {
        this.listeners.forEach((consumer) -> {
            try {
                consumer.accept(event);
            } catch (ClassCastException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
