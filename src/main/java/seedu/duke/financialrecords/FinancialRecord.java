package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class FinancialRecord{
    protected String description;
    protected LocalDate date;
    protected double amount;

    public FinancialRecord(String description, LocalDate date, double amount) throws KaChinnnngException {
        if (description == null || description.trim().isEmpty()) {
            throw new KaChinnnngException("Description cannot be empty!");
        }
        if (amount < 0) {
            throw new KaChinnnngException("Amount cannot be negative!");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new KaChinnnngException("Date cannot be in the future!");
        }
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public String getCategory() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return date.format(formatter);
    }

    public double getAmount() {
        return amount;
    }

    public void setDescription(String description) throws KaChinnnngException {
        if (description.trim().isEmpty()) {
            throw new KaChinnnngException("Description cannot be empty!");
        }
        this.description = description;
    }

    public void setDate(LocalDate date) throws KaChinnnngException {
        this.date = date;
    }

    public void setAmount(double amount) throws KaChinnnngException {
        this.amount = amount;
    }

    public String toString() {
        return "";
    }

}
