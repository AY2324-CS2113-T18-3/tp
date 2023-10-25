package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.expensetypes.MealType;
import java.time.LocalDate;

/**
 * Food class that inherits from expense.
 * Represents food expenses.
 */
public class Food extends Expense {
    private MealType mealType;

    /**
     * Food class constructor
     *
     * @param description
     * @param date
     * @param amount
     * @param mealType
     * @throws KaChinnnngException
     */
    public Food(String description, LocalDate date, double amount, MealType mealType) throws KaChinnnngException {
        super(description, date, amount);
        this.category = "food";
        this.mealType = mealType;
    }

    /**
     * Returns meal type
     * @return
     */
    public MealType getMealType() {
        return mealType;
    }

    /**
     * Returns a string that contains all the information on the expense record
     * @return
     */
    @Override
    public String toString() {
        return "Food Expense" + " (" + getMealType() + "): " + getDescription() +
                " | Date: " + getDateString() + " | Amount: $" + String.format("%.2f", getAmount());
    }
}
