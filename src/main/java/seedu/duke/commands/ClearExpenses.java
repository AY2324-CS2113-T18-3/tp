package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;

import java.util.ArrayList;

public class ClearExpenses {
    private ArrayList<Expense> expenses;
    public ClearExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }
    public void clearAllIncomes(){
        expenses.clear();
        System.out.println("You have cleared the expense list");
    }
}
