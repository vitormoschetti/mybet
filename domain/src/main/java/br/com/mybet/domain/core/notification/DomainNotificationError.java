package br.com.mybet.domain.core.notification;

import java.util.Objects;

public record DomainNotificationError(String message, String context, String clasz) implements INotificationError {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainNotificationError that = (DomainNotificationError) o;
        return Objects.equals(message, that.message) && Objects.equals(context, that.context) && Objects.equals(clasz, that.clasz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, context, clasz);
    }
}
