import org.example.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DateUtilsTest {
    DateUtils tanggal;

    @BeforeAll
    public void initClass() {
        tanggal = new DateUtils();
        System.out.println("Running DateUtils Test - Before All");
    }

    @Test
    void test2000IsLeapYear() {
        Assertions.assertTrue(tanggal.isLeapYear(2000));
    }

    @Test
    void test2100IsLeapYear() {
        Assertions.assertFalse(tanggal.isLeapYear(2100));
    }

    @Test
    void test1996IsLeapYear() {
        Assertions.assertTrue(tanggal.isLeapYear(1996));
    }

    @Test
    void test1999IsLeapYear() {
        Assertions.assertFalse(tanggal.isLeapYear(1999));
    }

    @Test
    void testInvalidMonth() {
        int month0 = 0;
        int month1 = 13;
        int month2 = -1;
        Exception exception0 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tanggal.getDaysInMonth(2003,month0);
        });
        Exception exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tanggal.getDaysInMonth(2003,month1);
        });
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tanggal.getDaysInMonth(2003,month2);
        });

        String expectedMessage0 = "Invalid month value: " + month0;
        String expectedMessage1 = "Invalid month value: " + month1;
        String expectedMessage2 = "Invalid month value: " + month2;
        String actualMessage0 = exception0.getMessage();
        String actualMessage1 = exception1.getMessage();
        String actualMessage2 = exception2.getMessage();

        Assertions.assertTrue(actualMessage0.contains(expectedMessage0));
        Assertions.assertTrue(actualMessage1.contains(expectedMessage1));
        Assertions.assertTrue(actualMessage2.contains(expectedMessage2));
    }

    @Test
    void testGetDaysInMonth() {
        Assertions.assertEquals(31,tanggal.getDaysInMonth(2003,3));
    }

    @Test
    void testGetDaysInMonth2() {
        Assertions.assertEquals(30,tanggal.getDaysInMonth(2003,4));
    }

    @Test
    void testGetDaysInMonth_NotInLeapYear() {
        Assertions.assertEquals(28,tanggal.getDaysInMonth(2003,2));
    }

    @Test
    void testGetDaysInMonth_InLeapYear() {
        Assertions.assertEquals(29,tanggal.getDaysInMonth(2004,2));
    }
}
