package seedu.duke.financialrecords;
import seedu.duke.commands.KaChinnnngException;

import java.time.LocalDate;

public class Income extends FinancialRecord {

    public Income(String description, LocalDate date, double amount) throws KaChinnnngException {
        super(description, date, amount);
    }

    @Override
    public String toString() {
        return "Income: " + getDescription() + 
                " | Date: " + getDateString()+ " | Amount: $" + String.format("%.2f", getAmount());
    }
}
