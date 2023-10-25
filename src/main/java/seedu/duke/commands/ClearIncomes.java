package seedu.duke.commands;

import seedu.duke.financialrecords.Income;

import java.util.ArrayList;

public class ClearIncomes {
    private ArrayList<Income> incomes;
    public ClearIncomes(ArrayList<Income> incomes){
        this.incomes = incomes;
    }

    public void clearAllIncomes(){
        incomes.clear();
        System.out.println("You have cleared the income list");
    }
}
