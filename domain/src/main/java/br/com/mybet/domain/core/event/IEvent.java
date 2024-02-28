package br.com.mybet.domain.core.event;

import java.time.Instant;

public interface IEvent<T> {

    String traceId();

    String name();

    Instant instantCreated();

    T payload();


}
