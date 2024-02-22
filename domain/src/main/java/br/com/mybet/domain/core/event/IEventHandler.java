package br.com.mybet.domain.core.event;

public interface IEventHandler<T> {

    void handle(IEvent<T> event);

}
