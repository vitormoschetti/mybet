package br.com.mybet.domain.core.event;

import java.util.List;

public interface IEventDispatcher<T> {

    void notify(IEvent<T> event);

    void register(final String eventName, final IEventHandler<T> handler);

    void unregister(final String eventName, final IEventHandler<T> handler);

    void unregisterAll();

    List<IEventHandler<T>> getEventHandlers(final String eventName);

    Boolean containsEvent(final String eventName);

}