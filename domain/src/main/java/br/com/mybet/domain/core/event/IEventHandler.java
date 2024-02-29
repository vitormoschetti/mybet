package br.com.mybet.domain.core.event;

public interface IEventHandler<T extends IEvent> {

    void handle(T event);

}
