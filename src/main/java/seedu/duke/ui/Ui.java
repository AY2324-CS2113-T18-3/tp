package seedu.duke.ui;
import java.util.List;
import java.util.Scanner;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;


/**
 * This class handles the user interface of the program.
 */
public class Ui {
    private static final String lineDivider = "____________________________________________________________";

    public Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * This method reads the command input by the user.
     *
     * @return String containing the command input by the user
     * @throws KaChinnnngException if there is no input provided by the user
     */
    public String readCommand() throws KaChinnnngException {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            throw new KaChinnnngException("No input provided by the user.");
        }
    }

    /**
     * This method prints the line divider.
     */
    public static void showLineDivider() {
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
        System.out.println(lineDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineDivider);
    }

    /**
     * prints the message when the user inputs his income
     *
     * @param income Income object created from the provided fields
     */
    public static void printIncomeAddedMessage(Income income) {
        System.out.println("Got it. I've added this income:\n" + income.toString());
    }

    /**
     * Prints a message indicating the start of the income list display.
     */
    public void printListIncomeMessage() {
        System.out.println("Here are your incomes:");
    }

    public void printExpenseAddedMessage(Expense expense) {
        System.out.println("Got it. I've added this expense:\n" + expense.toString());
    }

    public void printListExpenseMessage() {
        System.out.println("Here are your expenses:");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void showMatchingIncomes(List<Income> matchingIncomes) {
        if(matchingIncomes.isEmpty()) {
            System.out.println("No matching incomes found.");
        } else {
            System.out.println("Here are the matching incomes in your list:");
            for (Income income : matchingIncomes) {
                System.out.println(income.toString());
            }
        }
    }

    public void showMatchingExpenses(List<Expense> matchingExpenses) {
        if(matchingExpenses.isEmpty()) {
            System.out.println("No matching expenses found.");
        } else {
            System.out.println("Here are the matching expenses in your list:");
            for (Expense expense : matchingExpenses) {
                System.out.println(expense.toString());
            }
        }
    }
}
