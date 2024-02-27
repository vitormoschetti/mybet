package br.com.mybet.domain.bet.entity;

import br.com.mybet.domain.bet.enums.BetResult;
import br.com.mybet.domain.bet.enums.BetStatus;
import br.com.mybet.domain.bet.enums.BetType;
import br.com.mybet.domain.bet.vo.BetAmount;
import br.com.mybet.domain.bet.vo.BetDate;
import br.com.mybet.domain.bet.vo.BetOdd;
import br.com.mybet.domain.bet.vo.BetPotentialWinnings;
import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregate;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;
import br.com.mybet.domain.event.vo.EventDate;
import br.com.mybet.domain.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Bet extends BaseEntity implements IAggregate<User> {

    private UUID id;
    private UUID eventId;
    private BetType type;
    private BetAmount amount;
    private BetOdd odd;
    private BetStatus status;
    private BetDate betDate;
    private EventDate eventDate;
    private BetResult result;
    private BetPotentialWinnings potentialWinnings;

    public Bet() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(final String context) {

        if (Objects.isNull(this.id))
            this.addMessage(new DomainNotificationError("Id is required", context, this.getClass().getSimpleName()));

        if (Objects.isNull(this.eventId))
            this.addMessage(new DomainNotificationError("Event id is required", context, this.getClass().getSimpleName()));

        if (Objects.isNull(this.type))
            this.addMessage(new DomainNotificationError("Type is required", context, this.getClass().getSimpleName()));

        if (this.amount.hasErrors())
            this.amount.getMessages().forEach(this::addMessage);

        if (this.odd.hasErrors())
            this.odd.getMessages().forEach(this::addMessage);

        if (Objects.isNull(this.status))
            this.addMessage(new DomainNotificationError("Status is required", context, this.getClass().getSimpleName()));

        if (this.betDate.hasErrors())
            this.betDate.getMessages().forEach(this::addMessage);

        if (this.eventDate.hasErrors())
            this.eventDate.getMessages().forEach(this::addMessage);

        if (Objects.isNull(this.result))
            this.addMessage(new DomainNotificationError("Result is required", context, this.getClass().getSimpleName()));

        if (this.potentialWinnings.hasErrors())
            this.potentialWinnings.getMessages().forEach(this::addMessage);
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getEventId() {
        return this.eventId;
    }

    public BetType getType() {
        return this.type;
    }

    public BigDecimal getAmount() {
        return this.amount.getValue();
    }

    public BigDecimal getOdd() {
        return this.odd.getValue();
    }

    public BetStatus getStatus() {
        return this.status;
    }

    public LocalDate getBetDate() {
        return this.betDate.getDate();
    }

    public String getEventDate() {
        return this.eventDate.getDateTime();
    }

    public BetResult getResult() {
        return this.result;
    }

    public BigDecimal getPotentialWinnings() {
        return this.potentialWinnings.getValue();
    }

    public void create(
            final UUID eventId,
            final BetType type,
            final BigDecimal amount,
            final BigDecimal odd,
            final EventDate eventDate
    ) {
        this.id = UUID.randomUUID();
        this.eventId = eventId;
        this.type = type;
        this.amount = new BetAmount(amount);
        this.odd = new BetOdd(odd);
        this.status = BetStatus.ACTIVE;
        this.betDate = new BetDate();
        this.eventDate = eventDate;
        this.result = BetResult.SCHEDULED;
        this.potentialWinnings = new BetPotentialWinnings(this.amount, this.odd);
        this.validate("create");
    }


}
