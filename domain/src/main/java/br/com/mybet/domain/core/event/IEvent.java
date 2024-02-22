package br.com.mybet.domain.core.event;

import java.time.Instant;

public interface IEvent<T> {

    String traceId();

    Instant instantCreated();

    T payload();


}
