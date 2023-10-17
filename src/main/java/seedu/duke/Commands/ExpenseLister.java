package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;
import seedu.duke.ui.Ui;
import java.util.ArrayList;

public class ExpenseLister extends Commands {
    private final ArrayList<Expense> expenses;
    private final Ui ui;

    // Updated constructor
    public ExpenseLister(ArrayList<Expense> expenses, Ui ui) {
        this.expenses = expenses;
        this.ui = ui;
    }

    @Override
    public void execute() {
        listExpenses();
    }

    // Updated method name to follow Java naming conventions
    public void listExpenses() {
        if (expenses.isEmpty()) {
            ui.showLineDivider();
            System.out.println("You have no recorded expenses.");
            ui.showLineDivider();
            return;
        }

        ui.showLineDivider();
        System.out.println("Here are your expenses:");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i).toString());
        }
        ui.showLineDivider();
    }
}
