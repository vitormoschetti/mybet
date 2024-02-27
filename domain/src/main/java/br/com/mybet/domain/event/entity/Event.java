package br.com.mybet.domain.event.entity;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregateRoot;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.event.enums.EventOdds;
import br.com.mybet.domain.event.enums.EventResult;
import br.com.mybet.domain.event.enums.EventStatus;
import br.com.mybet.domain.event.vo.EventDate;
import br.com.mybet.domain.event.vo.Team;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event extends BaseEntity implements IAggregateRoot {

    private UUID id;
    private Team homeTeam;
    private Team awayTeam;
    private EventDate date;
    private String location;
    private EventStatus status;
    private EventResult result;
    private EventOdds odds;
    private LocalDateTime BetDeadline;

    private Event() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {

    }
}
