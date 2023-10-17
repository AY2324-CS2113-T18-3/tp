package seedu.duke.commands;

import seedu.duke.parser.IncomeParser;
import java.util.HashMap;
import seedu.duke.financialrecords.Income;

public class IncomeManager extends Commands{
    private final String details;
    private Income newIncome;

    public IncomeManager(String details) {
        this.details = details;
    }

    @Override
    public void execute() throws KaChinnnngException {
        HashMap<String, String> incomeFields = extractIncomeFields(details);
        newIncome = IncomeParser.parseIncome(incomeFields);
    }

    public Income getNewIncome() {
        return newIncome;
    }

    private HashMap<String, String> extractIncomeFields(String details) throws KaChinnnngException{
        HashMap<String,String> incomeFields = new HashMap<>();

        String[] parts = details.split("/description | /date | /amount ");

        if(parts.length != 4) {
            throw new KaChinnnngException("Missing fields detected");
        }
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, parts[1].trim());
        incomeFields.put(IncomeParser.DATE_FIELD, parts[2].trim());
        incomeFields.put(IncomeParser.AMOUNT_FIELD, parts[3].trim());

        return incomeFields;
    }
}
