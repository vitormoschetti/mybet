package br.com.mybet.domain.core.event;

import java.util.List;

public interface IEventDispatcher {

    void notify(IEvent event);

    void register(final String eventName, final IEventHandler<IEvent> handler);

    void unregister(final String eventName, final IEventHandler<IEvent> handler);

    void unregisterAll();

    List<IEventHandler<IEvent>> getEventHandlers(final String eventName);

    Boolean containsEvent(final String eventName);

}