package seedu.duke;


import seedu.duke.commands.*;
import seedu.duke.financialrecords.ExchangeRateManager;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
import seedu.duke.storage.ExchangeRateFileHandler;
import seedu.duke.storage.GetFromTxt;
import seedu.duke.storage.SaveToTxt;
import seedu.duke.ui.Ui;
import seedu.duke.parser.Parser;
import seedu.duke.parser.FindParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class is the main class of the program.
 * It contains the main method that runs the program.
 */
public class Duke {
    private Ui ui;
    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;
    private String storagePath;
    private SaveToTxt save;
    private GetFromTxt get;
    private ExchangeRateManager exchangeRateManager;
    private ExchangeRateFileHandler exchangeRateFileHandler;

    public Duke() {
        ui = new Ui();
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
        storagePath = "KaChinnnngggg.txt";
        save = new SaveToTxt(storagePath);
        get = new GetFromTxt(storagePath);
        exchangeRateManager = ExchangeRateManager.getInstance();
        exchangeRateFileHandler = new ExchangeRateFileHandler("./data/ExchangeRates.txt");
    }
    /**
     * This method runs the program.
     */
    public void run() {
        Ui.printWelcomeMessage();
        try {
            get.getFromTextFile(incomes, expenses);
            exchangeRateFileHandler.load();
        } catch (FileNotFoundException e) {
            System.out.println("\tOOPS!!! File not found.");
        } catch (KaChinnnngException e) {
            ui.showLineDivider();
            System.out.println(e.getMessage());
            ui.showLineDivider();
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String command = Parser.parse(fullCommand);
                String[] parts = command.split("-", 2);
                switch (parts[0]) {
                case "exit":
                    isExit = true;
                    break;

                case "add_income":
                    try{
                        IncomeManager incomeCommand = new IncomeManager(fullCommand);
                        incomeCommand.execute();
                        Income newIncome = incomeCommand.getNewIncome();
                        incomes.add(newIncome);
                        ui.printIncomeAddedMessage(newIncome);
                    } catch (KaChinnnngException e) {
                        Ui.showLineDivider();
                        System.out.println(e.getMessage());
                        Ui.showLineDivider();
                    }
                    break;

                case "list_income":
                    new IncomeLister(incomes, ui).listIncomes();
                    break;

                case "add_expense":
                    try{
                        ExpenseManager expenseCommand = new ExpenseManager(fullCommand);
                        expenseCommand.execute();
                        Expense newExpense = expenseCommand.getNewExpense();
                        expenses.add(newExpense);
                        ui.printExpenseAddedMessage(newExpense);
                    } catch (KaChinnnngException e) {
                        Ui.showLineDivider();
                        System.out.println(e.getMessage());
                        Ui.showLineDivider();
                    }
                    break;

                case "list_expense":
                    new ExpenseLister(expenses, ui).listExpenses();
                    break;

                case "list":
                    Ui.showLineDivider();
                    new ListCommand(incomes, expenses, ui).execute();
                    Ui.showLineDivider();
                    break;

                case "delete_income":
                    ui.showLineDivider();
                    new DeleteIncomeCommand().execute(incomes, fullCommand, ui);
                    ui.showLineDivider();
                    break;

                case "delete_expense":
                    ui.showLineDivider();
                    new DeleteExpenseCommand().execute(expenses, fullCommand, ui);
                    ui.showLineDivider();
                    break;

                case "help":
                    new UsageInstructions(ui).getHelp();
                    break;

                case "balance":
                    Ui.showLineDivider();
                    new Balance(incomes, expenses).getBalanceMessage();
                    Ui.showLineDivider();
                    break;
                case "find":
                    try {
                        String[] parsedParameters = FindParser.parseFindCommand(fullCommand);
                        FindCommand findCommand = new FindCommand(
                                incomes, expenses,
                                parsedParameters[0], parsedParameters[1],
                                parsedParameters[2], parsedParameters[3], ui);
                        findCommand.execute();
                    } catch (KaChinnnngException e) {
                        Ui.showLineDivider();
                        System.out.println(e.getMessage());
                    }
                    Ui.showLineDivider();
                    break;
                case "clear_incomes":
                    Ui.showLineDivider();
                    new ClearIncomes(incomes).clearAllIncomes();
                    Ui.showLineDivider();
                    break;
                case "clear_expenses":
                    Ui.showLineDivider();
                    new ClearExpenses(expenses).clearAllIncomes();
                    Ui.showLineDivider();
                    break;
                case "clear_all":
                    Ui.showLineDivider();
                    new ClearAll(incomes, expenses).clearAllIncomeAndExpense();
                    Ui.showLineDivider();
                    break;
                case "edit_income":
                    ui.showLineDivider();
                    new EditIncomeCommand(incomes, fullCommand).execute();
                    ui.showLineDivider();
                    break;

                case "edit_expense":
                    ui.showLineDivider();
                    new EditExpenseCommand(expenses, fullCommand).execute();
                    ui.showLineDivider();
                    break;

                case "list_currencies":
                    exchangeRateManager.showSupportedCurrencies();
                    break;

                case "list_exchange_rates":
                    exchangeRateManager.showExchangeRates();
                    break;
                case "update_exchange_rate":
                    Ui.showLineDivider();
                    Command c = new UpdateExchangeRateCommand(fullCommand, exchangeRateFileHandler);
                    c.execute();
                    Ui.showLineDivider();
                    break;
                default:
                    Ui.showLineDivider();
                    System.out.println("Invalid command. Please try again.");
                    Ui.showLineDivider();
                    break;
                }
            } catch (KaChinnnngException e) {
                System.out.println(e.getMessage());
                ui.showLineDivider();
            }
            save.saveIncomeAndExpense(incomes,expenses);
        }
        ui.printGoodbyeMessage();
    }


    public static void main(String[] args) {
        new Duke().run();
    }

}
