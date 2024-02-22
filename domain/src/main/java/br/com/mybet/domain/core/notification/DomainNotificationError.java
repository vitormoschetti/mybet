package br.com.mybet.domain.core.notification;

import java.util.Objects;

public record DomainNotificationError(String message, String context) implements INotificationError {

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final DomainNotificationError that)) return false;
        return Objects.equals(message, that.message) && Objects.equals(context, that.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, context);
    }
}
