package br.com.mybet.domain.bet.event;

import br.com.mybet.domain.bet.entity.Bet;
import br.com.mybet.domain.core.event.IEvent;
import br.com.mybet.domain.core.event.IEventDispatcher;
import br.com.mybet.domain.core.event.IEventHandler;

import java.util.*;

public class BetDispatcher implements IEventDispatcher<Bet> {

    private final Map<String, Set<IEventHandler<Bet>>> eventHandlers;

    public BetDispatcher() {
        this.eventHandlers = new HashMap<>();
    }

    @Override
    public void notify(IEvent<Bet> event) {
        this.eventHandlers.values().stream()
                .flatMap(Collection::stream)
                .toList()
                .forEach(e -> e.handle(event));
    }

    @Override
    public void register(String eventName, IEventHandler<Bet> handler) {
        if (!this.eventHandlers.containsKey(eventName)) {
            final var handlers = new HashSet<IEventHandler<Bet>>();
            handlers.add(handler);
            this.eventHandlers.put(eventName, handlers);

        } else {
            this.eventHandlers.get(eventName).add(handler);
        }
    }

    @Override
    public void unregister(String eventName, IEventHandler<Bet> handler) {
        if (this.eventHandlers.containsKey(eventName)) {
            this.eventHandlers.get(eventName).remove(handler);
            if (this.eventHandlers.get(eventName).isEmpty())
                this.eventHandlers.remove(eventName);
        }
    }

    @Override
    public void unregisterAll() {
        this.eventHandlers.clear();
    }

    @Override
    public List<IEventHandler<Bet>> getEventHandlers(String eventName) {
        if (this.eventHandlers.containsKey(eventName))
            return this.eventHandlers.get(eventName).stream().toList();
        return List.of();
    }

    @Override
    public Boolean containsEvent(String eventName) {
        return this.eventHandlers.containsKey(eventName);
    }
}
