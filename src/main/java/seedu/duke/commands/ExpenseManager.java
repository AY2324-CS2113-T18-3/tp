package seedu.duke.commands;

import seedu.duke.parser.ExpenseParser;
import java.util.HashMap;
import seedu.duke.financialrecords.Expense;

/**
 * This is the class that manages the creation of new expense
 */

public class ExpenseManager extends Commands{
    private final String details;
    private Expense newExpense;

    public ExpenseManager(String details) {
        this.details = details;
    }

    /**
     * This method is used to execute the command
     *
     * @throws KaChinnnngException
     */
    @Override
    public void execute() throws KaChinnnngException {
        HashMap<String, String> expenseFields = extractExpenseFields(details);
        newExpense = ExpenseParser.parseExpense(expenseFields);
    }

    /**
     * This method returns new expense created
     *
     * @return
     */
    public Expense getNewExpense() {
        return newExpense;
    }

    /**
     * This method extracts the fields of the expense.
     *
     * @param details String containing the details of the expense
     * @return expenseFields HashMap containing the fields of the income
     * @throws KaChinnnngException if there is an error in the command
     */
    private HashMap<String, String> extractExpenseFields(String details) throws KaChinnnngException{
        HashMap<String,String> expenseFields = new HashMap<>();

        String[] parts = details.split("/cat | /type | /de | /date | /amt ");

        if(parts.length != 6) {
            throw new KaChinnnngException("Missing fields detected");
        }
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, parts[1].trim());
        expenseFields.put(ExpenseParser.TYPE_FIELD, parts[2].trim());
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, parts[3].trim());
        expenseFields.put(ExpenseParser.DATE_FIELD, parts[4].trim());
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, parts[5].trim());

        return expenseFields;
    }
}
