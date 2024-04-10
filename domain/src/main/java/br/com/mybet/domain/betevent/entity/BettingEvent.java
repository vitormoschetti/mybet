package br.com.mybet.domain.betevent.entity;

import br.com.mybet.domain.betevent.enums.EventResult;
import br.com.mybet.domain.betevent.enums.EventStatus;
import br.com.mybet.domain.betevent.vo.EventDate;
import br.com.mybet.domain.betevent.vo.EventOdd;
import br.com.mybet.domain.betevent.vo.Team;
import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregateRoot;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class BettingEvent extends BaseEntity implements IAggregateRoot {

    private UUID id;
    private Team homeTeam;
    private Team awayTeam;
    private EventDate date;
    private String location;
    private EventStatus status;
    private EventResult result;
    private EventOdd odd;
    private LocalDateTime betDeadline;

    private BettingEvent() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {

        if (Objects.isNull(this.id))
            this.addMessage(new DomainNotificationError("Id is required", context, this.getClass().getSimpleName()));
        if (Objects.isNull(this.homeTeam))
            this.addMessage(new DomainNotificationError("Home team is required", context, this.getClass().getSimpleName()));
        if (Objects.isNull(this.awayTeam))
            this.addMessage(new DomainNotificationError("Away team is required", context, this.getClass().getSimpleName()));
        if (this.date.hasErrors())
            this.date.getMessages().forEach(this::addMessage);
        if (Objects.isNull(this.location))
            this.addMessage(new DomainNotificationError("Location is required", context, this.getClass().getSimpleName()));
        if (Objects.isNull(this.status))
            this.addMessage(new DomainNotificationError("Event status is required", context, this.getClass().getSimpleName()));
        if (Objects.isNull(this.result))
            this.addMessage(new DomainNotificationError("Event result is required", context, this.getClass().getSimpleName()));
        if (this.odd.hasErrors())
            this.odd.getMessages().forEach(this::addMessage);
        if (Objects.isNull(this.betDeadline))
            this.addMessage(new DomainNotificationError("Bet deadline is required", context, this.getClass().getSimpleName()));

    }
}
