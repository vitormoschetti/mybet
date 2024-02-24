package br.com.mybet.domain.bet.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IValueObject;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;

import java.math.BigDecimal;
import java.util.Objects;

public class BetOdd extends BaseEntity implements IValueObject {

    private final BigDecimal value;

    public BetOdd(BigDecimal amount) {
        super(new DomainNotification());
        this.value = amount;
        this.validate("create");
    }

    @Override
    protected void validate(String context) {
        if (Objects.isNull(this.value) || this.value.compareTo(BigDecimal.ZERO) < 1)
            this.addMessage(new DomainNotificationError("Odd must be greater than zero", context, this.getClass().getSimpleName()));
    }

    public BigDecimal getValue() {
        return value;
    }
}
