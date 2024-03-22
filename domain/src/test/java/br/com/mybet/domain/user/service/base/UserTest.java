package br.com.mybet.domain.user.service.base;

import br.com.mybet.domain.balancemanagement.enums.TransactionalMethod;
import br.com.mybet.domain.user.entity.User;

import java.math.BigDecimal;

public class UserTest {

    public User userCreate() {
        final var user = new User();
        user.create();
        return user;
    }

    public User userWithAmount(final BigDecimal amount, TransactionalMethod method) {
        final var user = this.userCreate();
        user.deposit(amount, method);
        return user;
    }



}
