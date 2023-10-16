package seedu.duke.financialrecords;

import org.junit.jupiter.api.Test;

import seedu.duke.commands.KaChinnnngException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IncomeTest {
    @Test
    public void shouldCreateIncomeWithGivenAttributes() throws KaChinnnngException {
        Income income = new Income("Salary", LocalDate.of(2023, 10, 12), 5000.00);
        assertNotNull(income);
        assertEquals("Salary", income.getDescription());
        assertEquals(LocalDate.of(2023, 10, 12), income.getDate());
        assertEquals(5000.00, income.getAmount());
    }

    @Test
    public void shouldReturnFormattedString() throws KaChinnnngException{
        Income income = new Income("Salary", LocalDate.of(2023, 10, 12), 5000.00);
        String expected = "Income: Salary | Date: 12/Oct/2023 | Amount: $5000.00";
        assertEquals(expected, income.toString());
    }

    @Test
    public void shouldThrowExceptionForEmptyDescription() {
        assertThrows(KaChinnnngException.class, () -> {
            new Income("", LocalDate.of(2023, 10, 12), 5000.00);
        });
    }

    @Test
    public void shouldThrowExceptionForNegativeAmount() {
        assertThrows(KaChinnnngException.class, () -> {
            new Income("Salary", LocalDate.of(2023, 10, 12), -5000.00);
        });
    }

    @Test
    public void shouldThrowExceptionForFutureDate() {
        assertThrows(KaChinnnngException.class, () -> {
            new Income("Salary", LocalDate.now().plusDays(1), 5000.00);
        });
    }
}
