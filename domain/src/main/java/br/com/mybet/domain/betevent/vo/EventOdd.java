package br.com.mybet.domain.betevent.vo;

import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IValueObject;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;

import java.math.BigDecimal;

public class EventOdd extends BaseEntity implements IValueObject {

    private BigDecimal homeWin;
    private BigDecimal awayWin;
    private BigDecimal draw;


    protected EventOdd() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {

        if (this.homeWin.compareTo(BigDecimal.ZERO) < 0)
            this.addMessage(new DomainNotificationError("Home win must be greater than zero", context, this.getClass().getSimpleName()));

        if (this.awayWin.compareTo(BigDecimal.ZERO) < 0)
            this.addMessage(new DomainNotificationError("Home win must be greater than zero", context, this.getClass().getSimpleName()));

        if (this.draw.compareTo(BigDecimal.ZERO) < 0)
            this.addMessage(new DomainNotificationError("Home win must be greater than zero", context, this.getClass().getSimpleName()));

    }

    public void create(final BigDecimal homeWin, final BigDecimal awayWin, final BigDecimal draw) {

        this.homeWin = homeWin;
        this.awayWin = awayWin;
        this.draw = draw;

        this.validate("create");

    }

    public void update(final BigDecimal homeWin, final BigDecimal awayWin, final BigDecimal draw) {

        this.homeWin = homeWin;
        this.awayWin = awayWin;
        this.draw = draw;

        this.validate("update");

    }

}
