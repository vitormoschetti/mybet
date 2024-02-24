package br.com.mybet.domain.bet.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IValueObject;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;

import java.math.BigDecimal;
import java.util.Objects;

public class BetPotentialWinnings extends BaseEntity implements IValueObject {

    private final BigDecimal value;

    public BetPotentialWinnings(BetAmount amount, BetOdd odd) {
        super(new DomainNotification());
        this.value = amount.getValue().multiply(odd.getValue());
        this.validate("create");
    }

    @Override
    protected void validate(String context) {
        if(Objects.isNull(this.value))
            this.addMessage(new DomainNotificationError("Potential winnings must be greater than zero", context, this.getClass().getSimpleName()));
    }

    public BigDecimal getValue() {
        return this.value;
    }
}
