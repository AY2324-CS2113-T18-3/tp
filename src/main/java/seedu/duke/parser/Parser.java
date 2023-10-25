package seedu.duke.parser;

import seedu.duke.commands.KaChinnnngException;

import java.io.File;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


/*
 * parser class to parse user input
 */
public class Parser {
    //private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    static {
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/Parser.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }

    /**
     * This method is used to parse the user input.
     * This method is used by the Main class in the application
     *
     * @param fullCommand String containing the user input
     * @return String containing the command to be executed
     */
    public static String parse(String fullCommand) {

        assert fullCommand != null : "fullCommand should not be null";

        String trimmedCommand = fullCommand.trim();
        String commandLowerCase = trimmedCommand.toLowerCase();

        LOGGER.log(Level.INFO, ("Parsing user input:") + fullCommand);

        String resultCommand;

        if (commandLowerCase.equals("exit")) {
            resultCommand = "exit";
        } else if (commandLowerCase.startsWith("add income")) {
            resultCommand = "add_income";
        } else if (commandLowerCase.equals("list income")) {
            resultCommand = "list_income";
        } else if (commandLowerCase.startsWith("add expense")) {
            resultCommand = "add_expense";
        } else if (commandLowerCase.equals(("list expense"))) {
            resultCommand = "list_expense";
        } else if (commandLowerCase.equals("help")) {
            resultCommand = "help";
        } else if (commandLowerCase.equals("list")) {
            resultCommand = "list";
        } else if (commandLowerCase.startsWith("delete income")) {
            resultCommand = "delete_income";
        } else if (commandLowerCase.startsWith("delete expense")) {
            resultCommand = "delete_expense";
        } else if (commandLowerCase.equals("balance")) {
            resultCommand = "balance";
        } else if (commandLowerCase.startsWith("find")) {
            resultCommand = "find";
        } else if (commandLowerCase.startsWith("clear incomes")) {
            resultCommand = "clear_incomes";
        } else if (commandLowerCase.startsWith("clear expenses")) {
            resultCommand = "clear_expenses";
        } else if (commandLowerCase.startsWith("clear all")) {
            resultCommand = "clear_all";
        } else {
            resultCommand = "invalid";
        }
        LOGGER.log(Level.INFO , ("Parsed command: ") + resultCommand);
        return resultCommand;
    }
}
