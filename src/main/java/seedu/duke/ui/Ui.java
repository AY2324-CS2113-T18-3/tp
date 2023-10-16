package seedu.duke.ui;
import java.util.Scanner;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
//import java.util.ArrayList;

/**
 * This class handles the user interface of the program.
 */
public class Ui {
    private static final String lineDivider = "____________________________________________________________";

    public Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() throws KaChinnnngException {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            throw new KaChinnnngException("No input provided by the user.");
        }
    }

    public void showLineDivider() {
        System.out.println(lineDivider);
    }

    /**
     * Prints the welcome message when the program starts.
     */
    public static void printWelcomeMessage() {
        System.out.println(lineDivider);
        System.out.println("Welcome to KaChinnnngggg! How may i assist you today?");
        System.out.println(lineDivider);
    }

    /**
     * Prints the goodbye message when the program ends.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineDivider);
    }

    public void printIncomeAddedMessage(Income income) {
        System.out.println("Got it. I've added this income: \n" + income.toString());
    }

    public void printListIncomeMessage() {
        System.out.println("Here are your incomes:");
    }

    public void printExpenseAddedMessage(Expense expense) {
        System.out.println("Got it. I've added this expense: \n" + expense.toString());
    }

    public void printListExpenseMessage() {
        System.out.println("Here are your expenses:");
    }
}
