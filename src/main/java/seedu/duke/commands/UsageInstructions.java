package seedu.duke.commands;

import seedu.duke.ui.Ui;

public class UsageInstructions {
    // Shows a message linking the user to the user guide of our app
    private final seedu.duke.ui.Ui ui;

    /**
     * The `UsageInstructions` class provides usage instructions for the application,
     * including command formats and a link to the user guide.
     */
    public UsageInstructions(Ui ui) {
        this.ui = ui;
    }

    /**
     * Displays usage instructions for the application, including command formats and a link to the user guide.
     */
    public void getHelp(){
        ui.showLineDivider();
        // Shows a message linking the user to the user guide of our app
        System.out.println("You can access our user guide by " +
                "https://docs.google.com/document/d/1BOz_v4eYQ8y7Dje6Jm6nqymi9jmrsb9MAohLCL_sLvI/edit?usp=sharing\n");
        // Displays the various commands that user can use including the respective formats of the commands
        System.out.println("Functions and their format:\n");
        System.out.println("Adding an entry: add\nFormat:\n" +
                "add income /de <description> /date <date> /amt [currency] <amount>\n" +
                "add expense /cat <category> /type <type> " +
                "/de <description> /date <date> /amt <amount>\n");
        System.out.println("Listing all entries: list\nFormat:\n" + "list\n" + "list income\n" +
                "list expense\n");
        System.out.println("Deleting an entry: delete\nFormat:\ndelete income <index_pos>\n" +
                "delete expense <index_pos>\n");
        System.out.println("Edit an entry: edit\nFormat:\n" +
                "edit income <index> /de <description> /date <date> /amt [currency] <amount>\n" +
                "edit expense <index> /cat <catergory> /type <type> /de <description> /date <date> " +
                "/amt [currency] <amount>\n");
        System.out.println("Check balance of income: balance\nFormat: " + "balance\n");
        System.out.println("Exiting the program: exit\nFormat: " + "exit");
        ui.showLineDivider();
    }
}
