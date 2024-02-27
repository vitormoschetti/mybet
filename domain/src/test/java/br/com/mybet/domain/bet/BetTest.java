package br.com.mybet.domain.bet;

import br.com.mybet.domain.bet.entity.Bet;
import br.com.mybet.domain.bet.enums.BetResult;
import br.com.mybet.domain.bet.enums.BetStatus;
import br.com.mybet.domain.bet.enums.BetType;
import br.com.mybet.domain.bet.shared.BetBaseTests;
import br.com.mybet.domain.bet.vo.BetAmount;
import br.com.mybet.domain.bet.vo.BetDate;
import br.com.mybet.domain.bet.vo.BetOdd;
import br.com.mybet.domain.bet.vo.BetPotentialWinnings;
import br.com.mybet.domain.event.vo.EventDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class BetTest extends BetBaseTests {

    @Test
    @DisplayName("Should create valid bet")
    void createValidBet() {

        final var eventId = this.createRandomId();
        final var type = BetType.HOME_WIN;
        final var betAmount = this.createValue(2D);
        final var betOdd = this.createValue(1.74D);
        final var eventDate = this.eventDate();

        final var bet = new Bet();
        bet.create(eventId, type, betAmount, betOdd, eventDate);

        assertFalse(bet.hasErrors());
        assertNotNull(bet.getId());
        assertEquals(eventId, bet.getEventId());
        assertEquals(type, bet.getType());
        assertEquals(betAmount, bet.getAmount());
        assertEquals(betOdd, bet.getOdd());
        assertEquals(BetStatus.ACTIVE, bet.getStatus());
        assertEquals(new BetDate().getDate(), bet.getBetDate());
        assertEquals(new EventDate(LocalDate.now(), LocalTime.of(16, 0, 0)).getDateTime(), bet.getEventDate());
        assertEquals(BetResult.SCHEDULED, bet.getResult());
        assertEquals(new BetPotentialWinnings(new BetAmount(betAmount), new BetOdd(betOdd)).getValue(), bet.getPotentialWinnings());

    }

    @Test
    @DisplayName("Should create bet with many errors")
    void createInvalidBet() {

    }


}
