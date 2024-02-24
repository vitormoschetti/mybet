package br.com.mybet.domain.bet.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IValueObject;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Objects;

public class BetDate extends BaseEntity implements IValueObject {

    private final LocalDate date;

    public BetDate() {
        super(new DomainNotification());
        this.date = Instant.now().atOffset(ZoneOffset.UTC).toLocalDate();
        this.validate("create");
    }

    @Override
    protected void validate(String context) {
        if(Objects.isNull(this.date))
            this.addMessage(new DomainNotificationError("Bet date is required", context, this.getClass().getSimpleName()));
    }

    public LocalDate getDate() {
        return this.date;
    }
}
