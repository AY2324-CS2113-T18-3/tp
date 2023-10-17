package seedu.duke.commands;

import seedu.duke.parser.ExpenseParser;
import java.util.HashMap;
import seedu.duke.financialrecords.Expense;

public class ExpenseManager extends Commands{
    private final String details;
    private Expense newExpense;

    public ExpenseManager(String details) {
        this.details = details;
    }

    @Override
    public void execute() throws KaChinnnngException {
        HashMap<String, String> expenseFields = extractExpenseFields(details);
        newExpense = ExpenseParser.parseExpense(expenseFields);
    }

    public Expense getNewExpense() {
        return newExpense;
    }

    private HashMap<String, String> extractExpenseFields(String details) throws KaChinnnngException{
        HashMap<String,String> expenseFields = new HashMap<>();

        String[] parts = details.split("/category | /description | /date | /amount ");

        if(parts.length != 5) {
            throw new KaChinnnngException("Missing fields detected");
        }
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, parts[1].trim());
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, parts[2].trim());
        expenseFields.put(ExpenseParser.DATE_FIELD, parts[3].trim());
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, parts[4].trim());

        return expenseFields;
    }
}
