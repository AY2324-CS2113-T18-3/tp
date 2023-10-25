package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import java.time.LocalDate;

/**
 * Transport class that inherits from expense.
 * Represents transportation expenses.
 */
public class Transport extends Expense {
    private TransportationType transportationType;

    /**
     * Transport class constructor
     *
     * @param description
     * @param date
     * @param amount
     * @param transportationType
     * @throws KaChinnnngException
     */
    public Transport(String description, LocalDate date, double amount,
                     TransportationType transportationType) throws KaChinnnngException {
        super(description, date, amount);
        this.transportationType = transportationType;
    }

    /**
     * Returns transportation type
     * @return
     */
    public TransportationType getTransportationType() {
        return transportationType;
    }

    /**
     * Returns a string that contains all the information on the expense record
     * @return
     */
    @Override
    public String toString() {
        return "Transportation Expense" + " (" + getTransportationType() + "): " + getDescription() +
                " | Date: " + getDateString() + " | Amount: $" + String.format("%.2f", getAmount());
    }
}
