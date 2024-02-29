package br.com.mybet.domain.user.event.event;

import br.com.mybet.domain.balancemanagement.entity.BalanceManagement;
import br.com.mybet.domain.core.event.IEvent;
import br.com.mybet.domain.core.event.TraceIdEvent;
import br.com.mybet.domain.user.event.record.TransactionaDepositEventRecord;

import java.time.Instant;
import java.time.ZoneOffset;

public class TransactionalDepositEvent implements IEvent {

    private final TraceIdEvent traceId;
    private final String name;
    private final Instant instantCreated;
    private final TransactionaDepositEventRecord payload;

    public TransactionalDepositEvent(BalanceManagement balanceManagement) {
        this.traceId = new TraceIdEvent(this.getClass().getSimpleName(), balanceManagement.getTransactionId());
        this.name = this.getClass().getSimpleName();
        this.instantCreated = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
        this.payload = new TransactionaDepositEventRecord();

    }

    @Override
    public String traceId() {
        return this.traceId.getTraceId();
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Instant instantCreated() {
        return this.instantCreated;
    }

    @Override
    public TransactionaDepositEventRecord payload() {
        return this.payload;
    }
}
