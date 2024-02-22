package br.com.mybet.domain.bet.entity;

import br.com.mybet.domain.bet.enums.BetResult;
import br.com.mybet.domain.bet.enums.BetStatus;
import br.com.mybet.domain.bet.enums.BetType;
import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregateRoot;
import br.com.mybet.domain.core.notification.INotification;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Bet extends BaseEntity implements IAggregateRoot {

   private UUID id;
   private UUID userId;
   private UUID eventId;
   private BetType type;
   private BigDecimal betAmount;
   private Double odd;
   private BetStatus status;
   private Instant betDate;
   private Instant eventDate;
   private BetResult result;
   private BigDecimal potencialWinnings;


    protected Bet(INotification notification) {
        super(notification);
    }

    @Override
    protected void validate() {

    }


}
