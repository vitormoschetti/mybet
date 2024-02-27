package br.com.mybet.domain.balancemanagement.entity;

import br.com.mybet.domain.balancemanagement.enums.TransactionalMethod;
import br.com.mybet.domain.balancemanagement.enums.TransactionalStatus;
import br.com.mybet.domain.balancemanagement.enums.TransactionalType;
import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregate;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.user.entity.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class BalanceManagement extends BaseEntity implements IAggregate<User> {

    private UUID transactionId;
    private TransactionalType type;
    private BigDecimal amount;
    private Instant timestamp;
    private TransactionalStatus status;
    private TransactionalMethod method;


    private BalanceManagement() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {

    }
}
