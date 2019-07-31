package by.talstaya.task03.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MatrixFileValidator {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private final int MIN_DIMENSION = 8;
    private final int MAX_DIMENSION = 12;

    public boolean isValid(final List<String> lines) {

        if (lines.size() < MIN_DIMENSION | lines.size() > MAX_DIMENSION) {
            return false;
        }

        int lineLength = lines.get(0).split("\\s").length;
        if (lineLength < MIN_DIMENSION || lineLength > MAX_DIMENSION) {
            return false;
        }

        for (String line : lines) {
            String[] values = line.split("\\s");
            if (values.length != lineLength) {
                return false;
            } else {
                for (String word : values) {
                    try {
                        int value = Integer.parseInt(word);
                        if (value < 0) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.fatal("Matrix file is invalid. There is incorrect format data in the file.");
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
