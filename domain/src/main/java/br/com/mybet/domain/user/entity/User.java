package br.com.mybet.domain.user.entity;

import br.com.mybet.domain.balancemanagement.entity.BalanceManagement;
import br.com.mybet.domain.bet.entity.Bet;
import br.com.mybet.domain.core.entity.BaseEntity;
import br.com.mybet.domain.core.entity.IAggregateRoot;
import br.com.mybet.domain.core.notification.DomainNotification;
import br.com.mybet.domain.user.vo.UserBalance;

import java.util.List;
import java.util.UUID;

public class User extends BaseEntity implements IAggregateRoot {

    private UUID id;
    private UserBalance accountBalance;
    private List<BalanceManagement> balanceManagements;
    private List<Bet> bets;

    private User() {
        super(new DomainNotification());
    }

    @Override
    protected void validate(String context) {

    }
}
