package br.com.mybet.domain.user.service;

import br.com.mybet.domain.balancemanagement.enums.TransactionalMethod;
import br.com.mybet.domain.core.entity.exception.DomainException;
import br.com.mybet.domain.user.entity.User;
import br.com.mybet.domain.user.enums.UserStatus;
import br.com.mybet.domain.user.event.dispatcher.UserDispatcher;
import br.com.mybet.domain.user.repository.UserRepository;
import br.com.mybet.domain.user.service.base.UserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest extends UserTest {

    UserDispatcher userDispatcher = new UserDispatcher();

    @Mock
    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    void setUp() {
        this.userService = new UserService(this.userDispatcher, this.userRepository);
    }

    @Test
    @DisplayName("Must create a user")
    void mustCreateAUser() {

        //cenario
        doNothing().when(this.userRepository).create(any(User.class));

        //execucao
        final var user = this.userService.create();

        //validacao
        assertFalse(user.hasErrors());
        assertNotNull(user.getId());
        assertEquals(BigDecimal.ZERO, user.getAccountBalance());
        assertEquals(0, user.getBalanceManagements().size());
        assertEquals(0, user.getBets().size());
        assertEquals(UserStatus.ACTIVE, user.getStatus());

    }

    @Test
    @DisplayName("Must make first deposit")
    void mustMakeFirstDeposit() {

        //cenario
        final var user = this.userCreate();
        when(this.userRepository.findById(any(UUID.class)))
                .thenReturn(user);

        doNothing().when(this.userRepository).update(any(User.class));

        //execucao
        this.userService.deposit(user.getId(), new BigDecimal(10), TransactionalMethod.ONLINE_PAYMENT);

        //validacao
        verify(this.userRepository, times(1)).findById(any(UUID.class));
        verify(this.userRepository, times(1)).update(any(User.class));

        assertFalse(user.hasErrors());
        assertEquals(1, user.getBalanceManagements().size());
        assertEquals(new BigDecimal(10), user.getAccountBalance());

    }

    @Test
    @DisplayName("Must sum deposit in user balance")
    void mustSumDepositUserBalance() {

        //cenario
        final var user = this.userWithAmount(BigDecimal.ONE, TransactionalMethod.ONLINE_PAYMENT);
        when(this.userRepository.findById(any(UUID.class)))
                .thenReturn(user);

        doNothing().when(this.userRepository).update(any(User.class));

        //execucao
        this.userService.deposit(user.getId(), BigDecimal.ONE, TransactionalMethod.ONLINE_PAYMENT);

        //validacao
        verify(this.userRepository, times(1)).findById(any(UUID.class));
        verify(this.userRepository, times(1)).update(any(User.class));

        assertEquals(new BigDecimal(2), user.getAccountBalance());
        assertEquals(2, user.getBalanceManagements().size());
        assertFalse(user.hasErrors());
    }


    @Test
    @DisplayName("Must withdraw when balance has limit")
    void mustWithdrawWhenBalanceHasLimit() {

        //scenario
        final var user = this.userWithAmount(BigDecimal.TEN, TransactionalMethod.BANK_TRANSFER);
        when(this.userRepository.findById(any(UUID.class))).thenReturn(user);
        doNothing().when(this.userRepository).update(any(User.class));

        //execution

        this.userService.withdraw(user.getId(), BigDecimal.ONE);

        //validation
        verify(this.userRepository, times(1)).findById(any(UUID.class));
        verify(this.userRepository, times(1)).update(any(User.class));

        assertEquals(new BigDecimal(9), user.getAccountBalance());
        assertEquals(2, user.getBalanceManagements().size());
        assertFalse(user.hasErrors());

    }

    @Test
    @DisplayName("Must withdraw and have a zero balance")
    void mustWithdrawAndHaveAZeroBalance() {

        //scenario
        final var user = this.userWithAmount(BigDecimal.TEN, TransactionalMethod.BANK_TRANSFER);
        when(this.userRepository.findById(any(UUID.class))).thenReturn(user);
        doNothing().when(this.userRepository).update(any(User.class));

        //execution

        this.userService.withdraw(user.getId(), BigDecimal.TEN);

        //validation
        verify(this.userRepository, times(1)).findById(any(UUID.class));
        verify(this.userRepository, times(1)).update(any(User.class));

        assertEquals(BigDecimal.ZERO, user.getAccountBalance());
        assertEquals(2, user.getBalanceManagements().size());
        assertFalse(user.hasErrors());

    }

    @Test
    @DisplayName("Mustn't withdraw when no have limit")
    void mustNotWithdrawWhenNoHaveLimit() {

        //scenario
        final var user = this.userWithAmount(BigDecimal.ONE, TransactionalMethod.BANK_TRANSFER);
        when(this.userRepository.findById(any(UUID.class))).thenReturn(user);

        //execution

        final var domainException = assertThrows(DomainException.class, () -> this.userService.withdraw(user.getId(), BigDecimal.TEN));

        //validation
        verify(this.userRepository, times(1)).findById(any(UUID.class));
        verify(this.userRepository, times(0)).update(any(User.class));

        assertEquals("User balance must be greater than or equal to zero", domainException.getMessage());

    }
}
