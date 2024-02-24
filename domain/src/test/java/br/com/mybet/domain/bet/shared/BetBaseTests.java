package br.com.mybet.domain.bet.shared;

import br.com.mybet.domain.event.EventBaseTests;
import br.com.mybet.domain.event.vo.EventDate;

import java.math.BigDecimal;
import java.util.UUID;

public class BetBaseTests {

    public UUID createRandomId() {
        return UUID.randomUUID();
    }

    public BigDecimal createValue(final double value) {
        return new BigDecimal(value);
    }

    public EventDate eventDate() {
        return new EventBaseTests().eventDate();
    }

}
