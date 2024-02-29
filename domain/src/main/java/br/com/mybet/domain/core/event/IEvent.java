package br.com.mybet.domain.core.event;

import java.time.Instant;

public interface IEvent {

    String traceId();

    String name();

    Instant instantCreated();

    IEventRecord payload();


}
