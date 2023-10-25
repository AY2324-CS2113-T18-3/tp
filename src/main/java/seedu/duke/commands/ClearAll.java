package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Income;

import java.util.ArrayList;

public class ClearAll {
    private ArrayList<Income> incomes;

    private ArrayList<Expense> expenses;

    public ClearAll(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        this.incomes = incomes;
        this.expenses = expenses;
    }

    public void clearAllIncomeAndExpense(){
        incomes.clear();
        expenses.clear();
        System.out.println("Both income and expense list cleared");
    }

}
