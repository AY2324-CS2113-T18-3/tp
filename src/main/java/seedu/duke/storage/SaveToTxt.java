package seedu.duke.storage;

import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.Transport;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The SaveToTxt class is responsible for saving financial records data to a text file.
 * It can save both Income and Expense objects to the specified file.
 *
 * This class handles exceptions for IO errors that may occur during file operations.
 */
public class SaveToTxt {
    private static final String IO_ERROR_MESSAGE = "An error occurred while saving tasks to the file.";
    private final String path;

    /**
     * Construct a new object of the SaveToTxt class with the file path.
     *
     * @param path The path to the text file where data will be saved.
     */
    public SaveToTxt(String path) {
        this.path = path;
    }

    /**
     * Saves both Income and Expense objects to the specified text file.
     *
     * @param incomes   The ArrayList of Income objects to save.
     * @param expenses  The ArrayList of Expense objects to save.
     */
    public void saveIncomeAndExpense(ArrayList<Income> incomes, ArrayList<Expense> expenses){
        try {
            saveIncomeToTextFile(incomes);
            saveExpenseToTextFile(expenses);
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        }
    }

    /**
     * Saves Income objects to the text file. Each Income object is represented as a line in the txt file.
     *
     * @param incomes The ArrayList of Income objects to save.
     * @throws IOException If an IO error occurs during the file write operation.
     */
    public void saveIncomeToTextFile(ArrayList<Income> incomes) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            for (Income income : incomes) {
                String incomeDescription = income.getDescription();
                String incomeDate = String.valueOf(income.getDate());
                String incomeAmount = String.valueOf(income.getAmount());
                String textToAdd = "I" + " | " + incomeDescription + " | " + incomeAmount + " | " + incomeDate + "\n";
                fw.write(textToAdd);
            }
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        }
    }

    /**
     * Saves Expense objects to the text file. Each Expense object is represented as a line in the file,
     * and the type of Expense (Food, Transport, or Utilities) is specified in the line.
     *
     * @param expenses The ArrayList of Expense objects to save.
     * @throws IOException If an IO error occurs during the file write operation.
     */
    public void saveExpenseToTextFile(ArrayList<Expense> expenses) throws IOException{
        try (FileWriter fw = new FileWriter(path, true)) {
            for (Expense expense : expenses) {
                String textToAdd = "";
                String expenseDescription = expense.getDescription();
                String expenseDate = String.valueOf(expense.getDate());;
                String expenseAmount = String.valueOf(expense.getAmount());

                // Determine the type of Expense and format the line accordingly
                if (expense.getClass() == Food.class) {
                    textToAdd = "EF" + " | " + expenseDescription + " | " + expenseAmount + " | "
                            + expenseDate + " | " + ((Food) expense).getMealType().ordinal() + "\n";
                } else if (expense.getClass() == Transport.class) {
                    textToAdd = "ET" + " | " + expenseDescription + " | " + expenseAmount + " | "
                            + expenseDate + " | " + ((Transport) expense).getTransportationType().ordinal() + "\n";
                } else if (expense.getClass() == Utilities.class) {
                    textToAdd = "EU" + " | " + expenseDescription + " | " + expenseAmount + " | "
                            + expenseDate + " | " + ((Utilities) expense).getUtilityType().ordinal() + "\n";
                }
                fw.write(textToAdd);
            }
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        }
    }
}
