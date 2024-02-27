package br.com.mybet.domain.event.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregate;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.event.entity.Event;

import java.util.UUID;

public class Team extends BaseEntity implements IAggregate<Event> {

    private UUID id;
    private Long externalId;
    private String name;


    protected Team() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {
    }
}
