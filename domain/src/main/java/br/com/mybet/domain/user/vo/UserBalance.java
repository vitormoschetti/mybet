package br.com.mybet.domain.user.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IValueObject;
import br.com.mybet.domain.core.notification.DomainNotification;

import java.math.BigDecimal;

public class UserBalance extends BaseEntity implements IValueObject {

    private BigDecimal value;

    protected UserBalance() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {

    }
}
