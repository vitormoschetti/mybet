package br.com.mybet.domain.core.event;

import java.util.UUID;

public class TraceIdEvent {

    private final UUID traceId;
    private final String context;
    private final UUID objectId;

    public TraceIdEvent(String context, UUID objectId) {
        this.traceId = UUID.randomUUID();
        this.context = context;
        this.objectId = objectId;
    }

    public String getTraceId() {
        return String.format("%s_%s_%s", this.traceId, this.context, this.objectId);
    }
}
