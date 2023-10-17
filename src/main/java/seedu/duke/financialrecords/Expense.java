/**
 * Implementation of the Expense class
 * Basic expense class inherits from FinancialRecord and does not include any additional attributes
 * Attribute "amount" contains a negative double which will make it easier to tally up the net total at the end
 */

package seedu.duke.financialrecords;
import seedu.duke.commands.KaChinnnngException;

import java.time.LocalDate;

public class Expense extends FinancialRecord {
    public Expense(String description, LocalDate date, double amount) throws KaChinnnngException {
        super(description, date, -amount);
    }

    @Override
    public String toString() {
        return "Expense: " + getDescription() +
                " | Date: " + getDateString() + " | Amount: $" + String.format("%.2f", -getAmount());
    }
}
