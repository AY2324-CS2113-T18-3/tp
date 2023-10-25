package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.expensetypes.UtilityType;
import java.time.LocalDate;

/**
 * Utilities class that inherits from expense.
 * Represents utility expenses.
 */
public class Utilities extends Expense {
    private UtilityType utilityType;

    /**
     * Utilities class constructor
     *
     * @param description
     * @param date
     * @param amount
     * @param utilityType
     * @throws KaChinnnngException
     */
    public Utilities(String description, LocalDate date, double amount,
                     UtilityType utilityType) throws KaChinnnngException {
        super(description, date, amount);
        this.utilityType = utilityType;
    }

    /**
     * Returns utility type
     * @return
     */
    public UtilityType getUtilityType() {
        return utilityType;
    }

    /**
     * Returns a string that contains all the information on the expense record
     * @return
     */
    @Override
    public String toString() {
        return "Utilities Expense" + " (" + getUtilityType() + "): " + getDescription() +
                " | Date: " + getDateString() + " | Amount: $" + String.format("%.2f", getAmount());
    }
}
