package br.com.mybet.domain.user.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IValueObject;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;

import java.math.BigDecimal;

public class UserBalance extends BaseEntity implements IValueObject {

    private BigDecimal value;

    public UserBalance() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {
        if(this.value.compareTo(BigDecimal.ZERO) < 0)
            this.addMessage(new DomainNotificationError("User balance must be greater than or equal to zero", context, this.getClass().getSimpleName()));
    }

    public void createAccount() {
        this.value = BigDecimal.ZERO;
        this.validate("createAccount");
    }

    public void deposit(BigDecimal amount) {
        this.value = this.value.add(amount);
        this.validate("deposit");
    }

    public void withdraw(BigDecimal amount) {
        this.value = this.value.subtract(amount);
        this.validate("withdraw");
    }

    public BigDecimal getValue() {
        return this.value;
    }

}
