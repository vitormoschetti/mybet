package br.com.mybet.domain.balancemanagement.entity;

import br.com.mybet.domain.balancemanagement.enums.TransactionalMethod;
import br.com.mybet.domain.balancemanagement.enums.TransactionalStatus;
import br.com.mybet.domain.balancemanagement.enums.TransactionalType;
import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregate;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;
import br.com.mybet.domain.user.entity.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

public class BalanceManagement extends BaseEntity implements IAggregate<User> {

    private UUID transactionId;
    private TransactionalType type;
    private BigDecimal amount;
    private Instant timestamp;
    private TransactionalStatus status;
    private TransactionalMethod method;


    public BalanceManagement() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            this.addMessage(new DomainNotificationError("amount must be greater than zero", context, this.getClass().getSimpleName()));
        if (Objects.isNull(this.method))
            this.addMessage(new DomainNotificationError("transactional method is required", context, this.getClass().getSimpleName()));
    }

    public void deposit(BigDecimal amount, TransactionalMethod method) {
        this.transactionId = UUID.randomUUID();
        this.type = TransactionalType.DEPOSIT;
        this.amount = amount;
        this.timestamp = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
        this.status = TransactionalStatus.PENDING;
        this.method = method;
        this.validate("deposit");
    }

    public UUID getTransactionId() {
        return this.transactionId;
    }
}
