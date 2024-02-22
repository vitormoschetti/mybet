package br.com.mybet.domain.core.notification;

import java.util.Set;

public interface INotification {

    void addMessage(INotificationError error);

    Set<INotificationError> messages();

}
