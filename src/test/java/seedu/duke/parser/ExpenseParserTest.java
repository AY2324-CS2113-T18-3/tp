package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.expensetypes.MealType;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseParserTest {
    /**
     * This method tests the parseDate method in ExpenseParser.
     * Specifically, it tests if the date is parsed correctly.
     *
     * @throws KaChinnnngException if the parsed date is invalid
     */
    @Test
    void parseDate_validDate_success() throws KaChinnnngException {
        LocalDate expectedDate = LocalDate.of(2020, 02, 29);
        LocalDate actualDate = ExpenseParser.parseDate("29/02/2020");
        assertEquals(expectedDate, actualDate);
    }

    /**
     * This method tests the parseDate method in ExpenseParser
     * Specifically, it tests if a KaChinnnngException is thrown when trying to parse an invalid day.
     */
    @Test
    void parseDate_invalidDay_exceptionThrown() {
        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseDate("32/02/2020"));
    }

    /**
     * This method tests the parseDate method in ExpenseParser
     * Specifically, it tests if a KaChinnnngException is thrown when trying to parse an invalid year.
     */
    @Test
    void parseDate_invalidDate_exceptionThrown() {
        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseDate("10/10/20202"));
    }

    /**
     * This method tests the parseDate method in ExpenseParser
     * Specifically, it tests if a KaChinnnngException is thrown when trying to parse a date that is in the future.
     */
    @Test
    void parseDate_futureDate_exceptionThrown() {
        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseDate("10/10/3021"));
    }

    /**
     * This method tests the parseExpense method in ExpenseParser.
     * It verifies if the food category are correctly parsed into an Expense object.
     *
     * @throws KaChinnnngException for invalid scenarios
     */
    @Test
    void testParseExpense_foodCategory_success() throws KaChinnnngException {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "3.50");
        expenseFields.put(ExpenseParser.TYPE_FIELD, "lunch");

        assertTrue(ExpenseParser.parseExpense(expenseFields) instanceof Food);
    }

    /**
     * This method tests the parseExpense method in ExpenseParser.
     * It verifies if the transport category are correctly parsed into an Expense object.
     *
     * @throws KaChinnnngException for invalid scenarios
     */
    @Test
    void testParseExpense_transportCategory_success() throws KaChinnnngException {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "transport");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Bus");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "1.50");
        expenseFields.put(ExpenseParser.TYPE_FIELD, "bus");

        assertTrue(ExpenseParser.parseExpense(expenseFields) instanceof Transport);
    }

    /**
     * This method tests the parseExpense method in ExpenseParser.
     * It verifies if the utilities category are correctly parsed into an Expense object.
     *
     * @throws KaChinnnngException for invalid scenarios
     */
    @Test
    void testParseExpense_utilitiesCategory_success() throws KaChinnnngException {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "utilities");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Electricity");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "50.00");
        expenseFields.put(ExpenseParser.TYPE_FIELD, "electricity");

        assertTrue(ExpenseParser.parseExpense(expenseFields) instanceof Utilities);
    }

    /**
     * This method tests the parseExpense method in ExpenseParser.
     * Tests if the expense parsing method throws an exception for an unrecognized category.
     *
     */
    @Test
    void testParseExpense_invalidCategory_exceptionThrown() {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "invalid");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "3.50");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    /**
     * This method tests the parseExpense method in ExpenseParser.
     * Tests if the expense parsing method throws an exception for missing amount fields.
     *
     */
    @Test
    void testParseExpense_missingAmountField_success() {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    /**
     * This method tests the parseExpense method in ExpenseParser.
     * Tests if the expense parsing method throws an exception for missing description fields.
     *
     */
    @Test
    void testParseExpense_missingDescriptionFields_success(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "3.50");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    /**
     * This method tests the parseExpense method in ExpenseParser.
     * Tests if the expense parsing method throws an exception for missing date fields.
     *
     */
    @Test
    void testParseExpense_missingDateField_success(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "3.50");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    /**
     * Tests if the getIndex method in ExpenseParser throws an exception for a valid index format.
     */
    @Test
    void testGetIndexValid() throws KaChinnnngException {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.INDEX_FIELD, "1");

        assertEquals(1, ExpenseParser.getIndex(expenseFields));
    }

    /**
     * Tests if the getIndex method in ExpenseParser throws an exception for an invalid index format.
     */
    @Test
    void testGetIndexInvalid() {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.INDEX_FIELD, "a");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.getIndex(expenseFields));
    }

    /**
     * Tests if the expense parsing method throws an exception when the provided amount exceeds the limit.
     */
    @Test
    void testParseAmount_aboveLimit_exceptionThrown(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "1000000.00");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    /**
     * Tests if the expense parsing method throws an exception when the category field is empty.
     */
    @Test
    void testParseCategory_emptyString_exceptionThrown(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "9999.00");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    /**
     * Tests if the expense parsing method throws an exception when the description field is empty.
     */
    @Test
    void testParseDescription_emptyString_exceptionThrown(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "9999.00");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    /**
     * Tests if the expense parsing method throws an exception when the date field is empty.
     */
    @Test
    void testParseDate_emptyString_exceptionThrown(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "9999.00");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    /**
     * Tests if the expense parsing method throws an exception when the amount field is empty.
     */
    @Test
    void testParseAmount_emptyString_exceptionThrown() {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }
}
