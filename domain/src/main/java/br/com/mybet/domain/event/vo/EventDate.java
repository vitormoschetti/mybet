package br.com.mybet.domain.event.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IValueObject;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class EventDate extends BaseEntity implements IValueObject {

    private final LocalDate date;
    private final LocalTime time;

    public EventDate(final LocalDate date, final LocalTime time) {
        super(new DomainNotification());
        this.time = time;
        this.date = date;
        this.validate("create");
    }

    @Override
    protected void validate(String context) {
        if (Objects.isNull(this.date))
            this.addMessage(new DomainNotificationError("Event date is required", context, this.getClass().getSimpleName()));
        if (Objects.isNull(this.time))
            this.addMessage(new DomainNotificationError("Event time is required", context, this.getClass().getSimpleName()));
    }

    public String getDateTime() {
        return String.format("%s %s", this.date.toString(), this.time.toString());
    }
}
