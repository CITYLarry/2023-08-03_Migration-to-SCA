package larry.ramirez.model.post.generic;

import java.util.List;

public abstract class AggregateRoot<I extends Identity> extends Entity<I> {

    private final ChangeEventSubscriber changeEventSubscriber;


    public AggregateRoot(I id) {
        super(id);
        changeEventSubscriber = new ChangeEventSubscriber();
    }


    protected ChangeEventSubscriber.ChangeApply appendChange(DomainEvent event) {
        var nameClass = identity().getClass().getSimpleName();
        var aggregate = nameClass.replaceAll("(Identity|Id|ID)", "").toLowerCase();
        event.setAggregateName(aggregate);
        event.setAggregateRootId(identity().value());
        return changeEventSubscriber.appendChange(event);
    }


    public List<DomainEvent> getUncommittedChanges() {
        return List.copyOf(changeEventSubscriber.changes());
    }

    protected final void subscribe(EventChange eventChange) {
        changeEventSubscriber.subscribe(eventChange);
    }

    public void markChangesAsCommitted() {
        changeEventSubscriber.changes().clear();
    }

    protected void applyEvent(DomainEvent domainEvent) {
        changeEventSubscriber.applyEvent(domainEvent);
    }
}
