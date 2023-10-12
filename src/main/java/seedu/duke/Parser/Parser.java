package seedu.duke.parser;

/*
 * parser class to parse user input
 */
public class Parser {
    public static String parse(String fullCommand) {
        String trimmedCommand = fullCommand.trim();
        String commandLowerCase = trimmedCommand.toLowerCase();

        if (commandLowerCase.equals("bye")) {
            return "exit";
        } else if (commandLowerCase.startsWith("add income")) {
            return "add_income";
        } else if (commandLowerCase.equals("list income")) {
            return "list_income";
        }
        return "invalid";
    }
}
