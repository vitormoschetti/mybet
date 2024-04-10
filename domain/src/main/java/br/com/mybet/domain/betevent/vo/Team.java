package br.com.mybet.domain.betevent.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregate;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.betevent.entity.BettingEvent;
import br.com.mybet.domain.core.notification.DomainNotificationError;

import java.util.Objects;
import java.util.UUID;

public class Team extends BaseEntity implements IAggregate<BettingEvent> {

    private UUID id;
    private Long externalId;
    private String name;


    protected Team() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {

        if(Objects.isNull(this.id))
            this.addMessage(new DomainNotificationError("Id is required", context, this.getClass().getSimpleName()));
        if(Objects.isNull(this.externalId))
            this.addMessage(new DomainNotificationError("externalId is required", context, this.getClass().getSimpleName()));
        if(Objects.isNull(this.name))
            this.addMessage(new DomainNotificationError("name is required", context, this.getClass().getSimpleName()));

    }
}
