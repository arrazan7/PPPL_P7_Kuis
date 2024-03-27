import org.example.BankAccount;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountTest {
    private List<BankAccount> bankAccountsList;
    private String methodName;

    @BeforeAll
    public void initClass() {
        bankAccountsList = new ArrayList<>();
        System.out.println("Running BankAccount Test - Before All");
    }

    @BeforeEach
    public void initMethod() {
        BankAccount akun1 = new BankAccount("Saya123", 500000);
        BankAccount akun2 = new BankAccount("Kamu456", 1000000);
        BankAccount akun3 = new BankAccount("Dia789", 1500000);
        bankAccountsList.add(akun1);
        bankAccountsList.add(akun2);
        bankAccountsList.add(akun3);
        System.out.println("Running BankAccount Test - Before Each");
    }

    @AfterEach
    public void cleanMethod() {
        bankAccountsList.clear();
        System.out.println("Running BankAccount Test - After Each - " + methodName);
    }

    @AfterAll
    public void cleanClass() {
        System.out.println("Running BankAccount Test - After All");
    }

    @Test
    void testConstructor() {
        methodName = "testConstructor";

        Assertions.assertFalse(bankAccountsList.isEmpty());
    }

    @Test
    void testGetAccountNumber() {
        methodName = "testGetAccountNumber";

        Assertions.assertEquals("Saya123", bankAccountsList.get(0).getAccountNumber());
        Assertions.assertEquals("Kamu456", bankAccountsList.get(1).getAccountNumber());
        Assertions.assertEquals("Dia789", bankAccountsList.get(2).getAccountNumber());
    }

    @Test
    void testGetBalance() {
        methodName = "testGetBalance";

        Assertions.assertEquals(500000, bankAccountsList.get(0).getBalance());
        Assertions.assertEquals(1000000, bankAccountsList.get(1).getBalance());
        Assertions.assertEquals(1500000, bankAccountsList.get(2).getBalance());
    }

    @Test
    void testDeposit() {
        methodName = "testDeposit";
        bankAccountsList.get(0).deposit(100000);
        bankAccountsList.get(1).deposit(100000);
        bankAccountsList.get(2).deposit(100000);

        Assertions.assertEquals(600000, bankAccountsList.get(0).getBalance());
        Assertions.assertEquals(1100000, bankAccountsList.get(1).getBalance());
        Assertions.assertEquals(1600000, bankAccountsList.get(2).getBalance());
    }

    @Test
    void testNegativeDeposit() {
        methodName = "testNegativeDeposit";
        Exception exception0 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(0).deposit(-100000);
        });
        Exception exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(1).deposit(-100000);
        });
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(2).deposit(-100000);
        });

        String expectedMessage = "Deposit amount cannot be negative.";
        String actualMessage0 = exception0.getMessage();
        String actualMessage1 = exception1.getMessage();
        String actualMessage2 = exception2.getMessage();

        Assertions.assertTrue(actualMessage0.contains(expectedMessage));
        Assertions.assertTrue(actualMessage1.contains(expectedMessage));
        Assertions.assertTrue(actualMessage2.contains(expectedMessage));
    }

    @Test
    void testWithdraw() {
        methodName = "testWithdraw";
        bankAccountsList.get(0).withdraw(100000);
        bankAccountsList.get(1).withdraw(100000);
        bankAccountsList.get(2).withdraw(100000);

        Assertions.assertEquals(400000, bankAccountsList.get(0).getBalance());
        Assertions.assertEquals(900000, bankAccountsList.get(1).getBalance());
        Assertions.assertEquals(1400000, bankAccountsList.get(2).getBalance());
    }

    @Test
    void testOverWithdraw() {
        methodName = "testOverWithdraw";
        Exception exception0 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(0).withdraw(600000);
        });
        Exception exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(1).withdraw(1100000);
        });
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(2).withdraw(1600000);
        });

        String expectedMessage = "Insufficient funds for withdraw";
        String actualMessage0 = exception0.getMessage();
        String actualMessage1 = exception1.getMessage();
        String actualMessage2 = exception2.getMessage();

        Assertions.assertTrue(actualMessage0.contains(expectedMessage));
        Assertions.assertTrue(actualMessage1.contains(expectedMessage));
        Assertions.assertTrue(actualMessage2.contains(expectedMessage));
    }

    @Test
    void testTransferFunds() {
        methodName = "testTransferFunds";
        bankAccountsList.get(0).transferFunds(bankAccountsList.get(1), 400000);
        bankAccountsList.get(1).transferFunds(bankAccountsList.get(2), 400000);
        bankAccountsList.get(2).transferFunds(bankAccountsList.get(0), 900000);

        Assertions.assertEquals(1000000, bankAccountsList.get(0).getBalance());
        Assertions.assertEquals(1000000, bankAccountsList.get(1).getBalance());
        Assertions.assertEquals(1000000, bankAccountsList.get(2).getBalance());
    }

    @Test
    void testOverTransferFunds() {
        methodName = "testOverTransferFunds";
        Exception exception0 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(0).transferFunds(bankAccountsList.get(1), 600000);
        });
        Exception exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(1).transferFunds(bankAccountsList.get(2), 1100000);
        });
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bankAccountsList.get(2).transferFunds(bankAccountsList.get(0), 1600000);
        });

        String expectedMessage = "Insufficient funds for transfer.";
        String actualMessage0 = exception0.getMessage();
        String actualMessage1 = exception1.getMessage();
        String actualMessage2 = exception2.getMessage();

        Assertions.assertTrue(actualMessage0.contains(expectedMessage));
        Assertions.assertTrue(actualMessage1.contains(expectedMessage));
        Assertions.assertTrue(actualMessage2.contains(expectedMessage));
    }
}
