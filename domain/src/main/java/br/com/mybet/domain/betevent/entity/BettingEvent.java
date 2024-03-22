package br.com.mybet.domain.betevent.entity;

import br.com.mybet.domain.betevent.vo.EventOdd;
import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregateRoot;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.betevent.enums.EventResult;
import br.com.mybet.domain.betevent.enums.EventStatus;
import br.com.mybet.domain.betevent.vo.EventDate;
import br.com.mybet.domain.betevent.vo.Team;

import java.time.LocalDateTime;
import java.util.UUID;

public class BettingEvent extends BaseEntity implements IAggregateRoot {

    private UUID id;
    private Team homeTeam;
    private Team awayTeam;
    private EventDate date;
    private String location;
    private EventStatus status;
    private EventResult result;
    private EventOdd odds;
    private LocalDateTime betDeadline;

    private BettingEvent() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {

    }
}
