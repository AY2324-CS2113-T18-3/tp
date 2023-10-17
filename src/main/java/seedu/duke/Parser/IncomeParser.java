package seedu.duke.parser;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;

public class IncomeParser {
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String AMOUNT_FIELD = "am";
    public static final String INDEX_FIELD = "in";

    public static LocalDate parseDate(String incomeDateString) throws KaChinnnngException {
        LocalDate incomeDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            incomeDate = LocalDate.parse(incomeDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new KaChinnnngException("Please enter a valid date in the format dd/MM/yyyy");
        }
        if (incomeDate.isAfter(LocalDate.now())) {
            throw new KaChinnnngException("Please enter a date that is not in the future");
        }
        return incomeDate;
    }

    public static Income parseIncome(HashMap<String, String> argumentsByField) throws KaChinnnngException {
        if (!argumentsByField.containsKey(DESCRIPTION_FIELD) ||
                !argumentsByField.containsKey(DATE_FIELD) ||
                !argumentsByField.containsKey(AMOUNT_FIELD)) {
            throw new KaChinnnngException("Missing fields detectedd");
        }

        String incomeDescriptionString = argumentsByField.get(DESCRIPTION_FIELD);
        String incomeDateString = argumentsByField.get(DATE_FIELD);
        String incomeAmountString = argumentsByField.get(AMOUNT_FIELD);
        LocalDate incomeDate = parseDate(incomeDateString);
        double incomeAmount;

        try {
            incomeAmount = Double.parseDouble(incomeAmountString);
        } catch (NumberFormatException e) {
            throw new KaChinnnngException("Please enter a valid amount");
        }

        if (incomeAmount > 999999.99 || incomeAmount <= 0) {
            throw new KaChinnnngException("Income amount must be between $0.01 and $999999.99");
        }
        return new Income(incomeDescriptionString, incomeDate, incomeAmount);
    }

    public static int getIndex(HashMap<String, String> argumentsByFields) throws KaChinnnngException {
        if (!argumentsByFields.containsKey(INDEX_FIELD)) {
            throw new KaChinnnngException("Missing index field detected");
        }
        try {
            return Integer.parseInt(argumentsByFields.get(INDEX_FIELD));
        } catch (NumberFormatException e) {
            throw new KaChinnnngException("Please enter a valid index");
        }
    }
}
