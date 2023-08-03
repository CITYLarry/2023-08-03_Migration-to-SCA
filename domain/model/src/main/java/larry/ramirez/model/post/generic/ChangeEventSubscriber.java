package larry.ramirez.model.post.generic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class ChangeEventSubscriber {

    private final List<DomainEvent> changes = new LinkedList<>();
    private final Map<String, AtomicLong> versions = new ConcurrentHashMap<>();
    private final Set<Consumer<? super DomainEvent>> observables = new HashSet<>();


    public List<DomainEvent> changes() {
        return changes;
    }

    public final ChangeApply appendChange(DomainEvent event) {
        changes.add(event);
        return () -> applyEvent(event);
    }

    public final void subscribe(EventChange eventChange) {
        this.observables.addAll(eventChange.behaviors);
    }

    public final void applyEvent(DomainEvent domainEvent) {
        observables.forEach(consumer -> {
            try {
                consumer.accept(domainEvent);
                var map = versions.get(domainEvent.type);
                long version = nextVersion(domainEvent, map);
                domainEvent.setVersionType(version);

            } catch (ClassCastException ignored) {
            }
        });
    }

    private long nextVersion(DomainEvent domainEvent, AtomicLong map) {
        if (map == null) {
            versions.put(domainEvent.type, new AtomicLong(domainEvent.versionType()));
            return domainEvent.versionType();
        }
        return versions.get(domainEvent.type).incrementAndGet();
    }


    @FunctionalInterface
    public interface ChangeApply {
        void apply();
    }
}
