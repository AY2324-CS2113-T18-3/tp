package seedu.duke.parser;

/*
 * parser class to parse user input
 */
public class Parser {
    /**
     * This method is used to parse the user input.
     * This method is used by the Main class in the application
     *
     * @param fullCommand String containing the user input
     * @return String containing the command to be executed
     */
    public static String parse(String fullCommand) {
        String trimmedCommand = fullCommand.trim();
        String commandLowerCase = trimmedCommand.toLowerCase();

        if (commandLowerCase.equals("bye")) {
            return "exit";
        } else if (commandLowerCase.startsWith("add income")) {
            return "add_income";
        } else if (commandLowerCase.equals("list income")) {
            return "list_income";
        } else if (commandLowerCase.equals("help")) {
            return "help";
        }
        return "invalid";
    }
}
