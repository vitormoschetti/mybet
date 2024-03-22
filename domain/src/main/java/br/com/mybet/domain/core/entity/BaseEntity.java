package br.com.mybet.domain.core.entity;

import br.com.mybet.domain.core.notification.INotification;
import br.com.mybet.domain.core.notification.INotificationError;

import java.util.List;
import java.util.Set;

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

    public List<String> getOnlyMessages() {
        return this.notification.messages().stream().toList().stream().map(INotificationError::message).toList();
    }

    public void addMessage(final INotificationError error) {
        this.notification.addMessage(error);
    }

    protected abstract void validate(final String context);


}
