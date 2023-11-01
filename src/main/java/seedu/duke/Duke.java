package seedu.duke;


import seedu.duke.commands.Balance;
import seedu.duke.commands.ClearAll;
import seedu.duke.commands.ClearExpenses;
import seedu.duke.commands.ClearIncomes;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteExpenseCommand;
import seedu.duke.commands.DeleteIncomeCommand;
import seedu.duke.commands.EditExpenseCommand;
import seedu.duke.commands.EditIncomeCommand;
import seedu.duke.commands.ExpenseLister;
import seedu.duke.commands.ExpenseManager;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.IncomeLister;
import seedu.duke.commands.IncomeManager;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.UpdateExchangeRateCommand;
import seedu.duke.commands.UsageInstructions;
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
        loadData();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if ("exit".equals(fullCommand)) {
                    isExit = true;
                    continue;
                }
                executeCommand(fullCommand);
                saveData();
            } catch (KaChinnnngException e) {
                Ui.showLineDivider();
                System.out.println(e.getMessage());
                Ui.showLineDivider();
            }
        }
        ui.printGoodbyeMessage();
    }

    protected void loadData() {
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
    }

    protected void executeCommand(String fullCommand) throws KaChinnnngException {
        String command = Parser.parse(fullCommand);
        String[] parts = command.split("-", 2);

        // ... (switch-case structure extracted from the original `run()` method)
        switch (parts[0]) {
        case "exit":
            break;

        case "add_income":
            try {
                IncomeManager incomeCommand = new IncomeManager(fullCommand);
                incomeCommand.execute();
                Income newIncome = incomeCommand.getNewIncome();
                incomes.add(newIncome);
                Ui.printIncomeAddedMessage(newIncome);
            } catch (KaChinnnngException e) {
                Ui.showLineDivider();
                throw e;
            }
            break;

        case "list_income":
            new IncomeLister(incomes, ui).listIncomes();
            break;

        case "add_expense":
            try {
                ExpenseManager expenseCommand = new ExpenseManager(fullCommand);
                expenseCommand.execute();
                Expense newExpense = expenseCommand.getNewExpense();
                expenses.add(newExpense);
                Ui.printExpenseAddedMessage(newExpense);
            } catch (KaChinnnngException e) {
                Ui.showLineDivider();
                System.out.println(e.getMessage());
                Ui.showLineDivider();
                throw e;
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
            Ui.showLineDivider();
            new DeleteIncomeCommand().execute(incomes, fullCommand, ui);
            Ui.showLineDivider();
            break;

        case "delete_expense":
            Ui.showLineDivider();
            new DeleteExpenseCommand().execute(expenses, fullCommand, ui);
            Ui.showLineDivider();
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
                FindCommand findCommand = new FindCommand(incomes, expenses,
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
            new ClearExpenses(expenses).clearAllExpenses();
            Ui.showLineDivider();
            break;

        case "clear_all":
            Ui.showLineDivider();
            new ClearAll(incomes, expenses).clearAllIncomeAndExpense();
            Ui.showLineDivider();
            break;

        case "edit_income":
            Ui.showLineDivider();
            new EditIncomeCommand(incomes, fullCommand).execute();
            Ui.showLineDivider();
            break;

        case "edit_expense":
            Ui.showLineDivider();
            new EditExpenseCommand(expenses, fullCommand).execute();
            Ui.showLineDivider();
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
            throw new KaChinnnngException("Invalid command. Please try again."
                    + "\nType 'help' to see the list of commands available.");
        }
    }

    private void saveData() {
        save.saveIncomeAndExpense(incomes, expenses);
    }


    public static void main(String[] args) {
        new Duke().run();
    }

    public int getIncomesSize() {
        return this.incomes.size();
    }

    public int getExpensesSize() {
        return this.expenses.size();
    }

}
