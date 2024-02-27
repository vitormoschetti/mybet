package br.com.mybet.domain.core.entity;

import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.INotification;
import br.com.mybet.domain.core.notification.INotificationError;

import java.util.Set;
import java.util.UUID;

public abstract class BaseEntity {

    private final INotification notification;

    protected BaseEntity(final INotification notification) {
        this.notification = notification;
    }


    public Boolean hasErrors() {
        return !this.notification.messages().isEmpty();
    }

    public Set<INotificationError> getMessages() {
        return this.notification.messages();
    }

    public void addMessage(final INotificationError error) {
        this.notification.addMessage(error);
    }

    protected abstract void validate(final String context);


}
