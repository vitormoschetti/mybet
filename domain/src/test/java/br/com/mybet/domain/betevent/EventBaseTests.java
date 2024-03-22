package br.com.mybet.domain.betevent;

import br.com.mybet.domain.betevent.vo.EventDate;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventBaseTests {

    public EventDate eventDate() {
        return new EventDate(LocalDate.now(), LocalTime.of(16, 0, 0));
    }

}
