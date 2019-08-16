package by.talstaya.crackertracker.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ProductDataValidator is used to validate search data of products
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ProductDataValidator {

    private static final String STRING_REGEX_SEARCH_LINE = "^.{0,100}$";
    private static final String STRING_REGEX_CALORIES = "^(([1-9]\\d{0,5})|(0))$";
    private static final String STRING_REGEX_PROTEINS = "^(([1-9]\\d{0,5})|(0))$";
    private static final String STRING_REGEX_LIPIDS = "^(([1-9]\\d{0,5})|(0))$";
    private static final String STRING_REGEX_CARBOHYDRATES = "^(([1-9]\\d{0,5})|(0))$";

    private static final Pattern patternSearchLine = Pattern.compile(STRING_REGEX_SEARCH_LINE);
    private static final Pattern patternCalories = Pattern.compile(STRING_REGEX_CALORIES);
    private static final Pattern patternProteins = Pattern.compile(STRING_REGEX_PROTEINS);
    private static final Pattern patternLipids = Pattern.compile(STRING_REGEX_LIPIDS);
    private static final Pattern patternCarbohydrates = Pattern.compile(STRING_REGEX_CARBOHYDRATES);


    public boolean validateData(String searchLine) {
        Matcher searchLineMatcher = patternSearchLine.matcher(searchLine);
        return searchLineMatcher.matches();
    }

    public boolean validateData(String searchLine,
                                String minCalories, String maxCalories,
                                String minProteins, String maxProteins,
                                String minLipids, String maxLipids,
                                String minCarbohydrates, String maxCarbohydrates) {

        Matcher searchLineMatcher = patternSearchLine.matcher(searchLine);
        Matcher minCaloriesMatcher = patternCalories.matcher(minCalories);
        Matcher maxCaloriesMatcher = patternCalories.matcher(maxCalories);
        Matcher minProteinsMatcher = patternProteins.matcher(minProteins);
        Matcher maxProteinsMatcher = patternProteins.matcher(maxProteins);
        Matcher minLipidsMatcher = patternLipids.matcher(minLipids);
        Matcher maxLipidsMatcher = patternLipids.matcher(maxLipids);
        Matcher minCarbohydratesMatcher = patternCarbohydrates.matcher(minCarbohydrates);
        Matcher maxCarbohydratesMatcher = patternCarbohydrates.matcher(maxCarbohydrates);

        return searchLineMatcher.matches()
                && minCaloriesMatcher.matches()
                && maxCaloriesMatcher.matches()
                && minProteinsMatcher.matches()
                && maxProteinsMatcher.matches()
                && minLipidsMatcher.matches()
                && maxLipidsMatcher.matches()
                && minCarbohydratesMatcher.matches()
                && maxCarbohydratesMatcher.matches();
    }
}
