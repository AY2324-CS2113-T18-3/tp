package seedu.duke.parser;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;

public class ExpenseParser {
    public static final String CATEGORY_FIELD = "ca";
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String AMOUNT_FIELD = "am";
    public static final String INDEX_FIELD = "in";

    public static LocalDate parseDate(String expenseDateString) throws KaChinnnngException {
        LocalDate expenseDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            expenseDate = LocalDate.parse(expenseDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new KaChinnnngException("Please enter a valid date in the format dd/MM/yyyy");
        }
        if (expenseDate.isAfter(LocalDate.now())) {
            throw new KaChinnnngException("Please enter a date that is not in the future");
        }
        return expenseDate;
    }

    public static Expense parseExpense(HashMap<String, String> argumentsByField) throws KaChinnnngException {
        if (!argumentsByField.containsKey(CATEGORY_FIELD) ||
                !argumentsByField.containsKey(DESCRIPTION_FIELD) ||
                !argumentsByField.containsKey(DATE_FIELD) ||
                !argumentsByField.containsKey(AMOUNT_FIELD)) {
            throw new KaChinnnngException("Missing fields detected");
        }

        String expenseCategoryString = argumentsByField.get(CATEGORY_FIELD).toLowerCase();
        String expenseDescriptionString = argumentsByField.get(DESCRIPTION_FIELD);
        String expenseDateString = argumentsByField.get(DATE_FIELD);
        String expenseAmountString = argumentsByField.get(AMOUNT_FIELD);
        LocalDate expenseDate = parseDate(expenseDateString);
        double expenseAmount;

        try {
            expenseAmount = Double.parseDouble(expenseAmountString);
        } catch (NumberFormatException e) {
            throw new KaChinnnngException("Please enter a valid amount");
        }

        if (expenseAmount > 999999.99 || expenseAmount <= 0) {
            throw new KaChinnnngException("Expense amount must be between $0.01 and $999999.99");
        }

        if (expenseCategoryString.equals("food")) {
            return new Food(expenseDescriptionString, expenseDate, expenseAmount, MealType.NONE);
        } else if (expenseCategoryString.equals("transport")){
            return new Transport(expenseDescriptionString, expenseDate, expenseAmount, TransportationType.NONE);
        } else {
            return new Utilities(expenseDescriptionString, expenseDate, expenseAmount, UtilityType.NONE);
        }
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
