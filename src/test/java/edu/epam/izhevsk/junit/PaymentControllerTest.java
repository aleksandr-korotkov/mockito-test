package edu.epam.izhevsk.junit;

import org.junit.Before;
import static org.mockito.AdditionalMatchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {
    @Mock
    private static AccountService accountService;
    @Mock
    private static DepositService depositService;
    @InjectMocks
    private final static PaymentController paymentController = new PaymentController(accountService,depositService);
    @Before
    public void setup() throws InsufficientFundsException {
        when(accountService.isUserAuthenticated(100l)).thenReturn(true);
        when(depositService.deposit(geq(100l),any(Long.class))).thenThrow(new InsufficientFundsException());
        when(depositService.deposit(leq(99l),any(Long.class))).thenReturn("successful");
    }

    @Test
    public void isUserAuthenticatedCalledOneTimeTest() throws InsufficientFundsException {
        paymentController.deposit(50l,100l);
        verify(accountService, times(1)).isUserAuthenticated(100l);
    }

    @Test(expected = SecurityException.class)
    public void failDepositForUnauthenticatedUserTest() throws InsufficientFundsException {
        paymentController.deposit(50l,99l);
    }

    @Test(expected = InsufficientFundsException.class)
    public void failDepositOfLargeAmountTest() throws InsufficientFundsException {
        paymentController.deposit(150l,100l);
    }
}