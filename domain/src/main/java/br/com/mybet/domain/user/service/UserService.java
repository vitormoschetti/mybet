package br.com.mybet.domain.user.service;

import br.com.mybet.domain.balancemanagement.enums.TransactionalMethod;
import br.com.mybet.domain.core.entity.exception.DomainException;
import br.com.mybet.domain.core.service.IService;
import br.com.mybet.domain.user.entity.User;
import br.com.mybet.domain.user.event.dispatcher.UserDispatcher;
import br.com.mybet.domain.user.event.event.TransactionalDepositEvent;
import br.com.mybet.domain.user.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class UserService implements IService {

    private final UserDispatcher userDispatcher;
    private final UserRepository userRepository;

    public UserService(final UserDispatcher userDispatcher, final UserRepository userRepository) {
        this.userDispatcher = userDispatcher;
        this.userRepository = userRepository;
    }

    public User create() {

        final var user = new User();

        user.create();

        this.userRepository.create(user);

        return user;

    }

    public void deposit(final UUID userId, final BigDecimal amount, final TransactionalMethod method) {

        final var user = this.userRepository.findById(userId);

        if (Objects.isNull(user))
            throw new DomainException(String.format("User with id: %s not found", userId));

        final var balanceManagement = user.deposit(amount, method);

        if (user.hasErrors())
            throw new DomainException(String.join(",", user.getOnlyMessages()));

        this.userRepository.update(user);

        this.userDispatcher.notify(new TransactionalDepositEvent(balanceManagement));

    }

    public void withdraw(final UUID userId, final BigDecimal amount) {

        final var user = this.userRepository.findById(userId);

        if (Objects.isNull(user))
            throw new DomainException(String.format("User with id: %s not found", userId));

        final var balanceManagement = user.withdraw(amount);

        if (user.hasErrors())
            throw new DomainException(String.join(",", user.getOnlyMessages()));

        this.userRepository.update(user);

        this.userDispatcher.notify(new TransactionalDepositEvent(balanceManagement));
    }

    public User findUser(final UUID userId) {

        final var user = this.userRepository.findById(userId);

        if (Objects.isNull(user))
            throw new DomainException(String.format("User with id: %s not found", userId));
 
        return user;

    }

}
