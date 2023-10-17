package seedu.duke.commands;

import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;
import java.util.ArrayList;

public class IncomeLister extends Commands {
    private final ArrayList<Income> incomes;
    private final Ui ui;

    // Updated constructor
    public IncomeLister(ArrayList<Income> incomes, Ui ui) {
        this.incomes = incomes;
        this.ui = ui;
    }

    @Override
    public void execute() {
        listIncomes();
    }

    // Updated method name to follow Java naming conventions
    public void listIncomes() {
        if (incomes.isEmpty()) {
            ui.showLineDivider();
            System.out.println("You have no recorded incomes.");
            ui.showLineDivider();
            return;
        }

        ui.showLineDivider();
        System.out.println("Here are your incomes:");
        for (int i = 0; i < incomes.size(); i++) {
            System.out.println((i + 1) + ". " + incomes.get(i).toString());
        }
        ui.showLineDivider();
    }
}
