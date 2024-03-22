package br.com.mybet.domain.user.entity;

import br.com.mybet.domain.balancemanagement.entity.BalanceManagement;
import br.com.mybet.domain.balancemanagement.enums.TransactionalMethod;
import br.com.mybet.domain.bet.entity.Bet;
import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregateRoot;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.core.notification.DomainNotificationError;
import br.com.mybet.domain.user.enums.UserStatus;
import br.com.mybet.domain.user.vo.UserBalance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User extends BaseEntity implements IAggregateRoot {

    private UUID id;
    private UserBalance accountBalance;
    private List<BalanceManagement> balanceManagements;
    private List<Bet> bets;
    private UserStatus status;

    public User() {
        super(new DomainNotification());
        this.accountBalance = new UserBalance();
        this.balanceManagements = new ArrayList<>();
        this.bets = new ArrayList<>();
    }

    @Override
    protected void validate(String context) {
        if (Objects.isNull(this.id))
            this.addMessage(new DomainNotificationError("Id is required", context, this.getClass().getSimpleName()));

        if (this.accountBalance.hasErrors())
            this.accountBalance.getMessages().forEach(this::addMessage);

        if (this.balanceManagements.stream().anyMatch(BaseEntity::hasErrors))
            this.balanceManagements.stream().filter(BaseEntity::hasErrors).map(BaseEntity::getMessages)
                    .forEach(iNotificationErrors -> iNotificationErrors.forEach(this::addMessage));

        if (this.bets.stream().anyMatch(BaseEntity::hasErrors))
            this.bets.stream().filter(BaseEntity::hasErrors).map(BaseEntity::getMessages)
                    .forEach(iNotificationErrors -> iNotificationErrors.forEach(this::addMessage));

    }

    public void create() {
        this.id = UUID.randomUUID();
        this.accountBalance.createAccount();
        this.status = UserStatus.ACTIVE;
    }

    public BalanceManagement deposit(BigDecimal amount, TransactionalMethod method) {
        final var balanceManagement = new BalanceManagement();
        balanceManagement.deposit(amount, method);
        this.accountBalance.deposit(amount);
        this.balanceManagements.add(balanceManagement);

        this.validate("deposit");

        return balanceManagement;

    }

    public BalanceManagement withdraw(BigDecimal amount) {
        final var balanceManagement = new BalanceManagement();
        balanceManagement.withdraw(amount);
        this.accountBalance.withdraw(amount);
        this.balanceManagements.add(balanceManagement);

        this.validate("withdraw");

        return balanceManagement;

    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAccountBalance() {
        return this.accountBalance.getValue();
    }

    public List<BalanceManagement> getBalanceManagements() {
        return balanceManagements;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public UserStatus getStatus() {
        return status;
    }



}
