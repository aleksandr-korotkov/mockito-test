# mockito-test
homework

Checkout https://bitbucket.org/alexandermrd/junitmocking.git
Implement tests for PaymentController class.
Mock Account and DepositService for it using Mockito (use @InjectMocks annotation).
Do not implement services themselves, test using mocks.
Configure mocks that for user with id 100 isAuthenticated will return “true”. For deposit of amount less than hundred transaction (any userId) will be successful, any other – will throw InsufficientFundsException  (use Mockito AdditionalMatchers). Mock initialisation should be done in one place, for each test.

Test:
1.	Successful deposit (userId 100, amount 50), check that isUserAuthenticated has been called exactly one time with parameter = 100.
2.	Failed deposit for unauthenticated user
3.	Failed deposit of large amount, expect exception
