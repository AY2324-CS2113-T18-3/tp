package seedu.duke.storage;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The GetFromTxt class is responsible for reading financial records data from a text file.
 * It parses the contents of the file and populates two ArrayLists: one for Incomes and one for Expenses.
 *
 * This class handles exceptions for file creation errors, parsing errors, and other issues that may arise
 * when reading data from the file.
 */
public class GetFromTxt {
    private static final String AMOUNT_NOT_SUPPORT_ERROR =
            "Amount field from this line is not supported from storage file ";

    private static final String DATE_TIME_FORMAT_ERROR =
            "Date time format incorrect on this line from storage file ";

    private static final String FILE_CREATION_ERROR =
            "Something went wrong while creating the file from storage file ";

    private static final String INDEX_OUT_OF_BOUND_FROM_FILE_ERROR =
            "Details missed on this line from storage file ";

    private static final String NUMBER_FROM_FILE_INCORRECT =
            "The expense type is incorrect on this line from storage file ";

    private final File file;

    /**
     * Construct a new object of the GetFromTxt class with the provided file path.
     *
     * @param path The path to the text file containing financial records data.
     */
    public GetFromTxt(String path) {
        file = new File(path);
    }

    /**
     * Reads financial records data from the text file and add to the corresponding ArrayLists
     * with Income and Expense objects.
     *
     * @param incomes   The ArrayList to store Income objects.
     * @param expenses  The ArrayList to store Expense objects.
     * @throws FileNotFoundException If the file specified by the path cannot be found.
     */
    public void getFromTextFile(ArrayList<Income> incomes, ArrayList<Expense> expenses) throws FileNotFoundException {
        try {
            if (file.createNewFile()) {
                return; // If there is no such file in the directory, create the file and return without reading data.
            }
        } catch (IOException e) {
            System.out.println(FILE_CREATION_ERROR);
        }
        Scanner s = new Scanner(file);                      // Create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            try {
                // Parse the data from the text line
                int expenseType = 0;
                String commandFromFile = textLine.split(" \\| ")[0];
                String descriptionFromFile = textLine.split(" \\| ")[1];
                double amountFromFile = Double. parseDouble(textLine.split(" \\| ")[2]);
                LocalDate dateFromFile =  LocalDate.parse(textLine.split(" \\| ")[3]);
                // Check for valid amount range
                if (amountFromFile > 999999.99 || amountFromFile <= 0) {
                    throw new KaChinnnngException("Expense amount must be between $0.01 and $999999.99");
                }
                if (!commandFromFile.equals("I")){
                    expenseType = Integer.parseInt(textLine.split(" \\| ")[4]);
                }
                // Create appropriate objects based on the commandFromFile
                switch (commandFromFile) {
                case "I":
                    incomes.add(new Income(descriptionFromFile, dateFromFile, amountFromFile));
                    break;
                case "EF":
                    expenses.add(new Food(descriptionFromFile, dateFromFile,
                            amountFromFile, MealType.values()[expenseType]));
                    break;
                case "ET":
                    expenses.add(new Transport(descriptionFromFile, dateFromFile,
                            amountFromFile, TransportationType.values()[expenseType]));
                    break;
                case "EU":
                    expenses.add(new Utilities(descriptionFromFile, dateFromFile,
                            amountFromFile, UtilityType.values()[expenseType]));
                    break;
                default:
                    System.out.println("The format of this line is incorrect " + "\"" + textLine + "\"");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INDEX_OUT_OF_BOUND_FROM_FILE_ERROR + "\"" + textLine + "\"");
            } catch (KaChinnnngException e) {
                System.out.println(AMOUNT_NOT_SUPPORT_ERROR + "\"" + textLine + "\"");
            } catch (DateTimeParseException e){
                System.out.println(DATE_TIME_FORMAT_ERROR + "\"" + textLine + "\"");
            } catch(NumberFormatException e){
                System.out.println(NUMBER_FROM_FILE_INCORRECT + "\"" + textLine + "\"");
            }
        }
    }
}
