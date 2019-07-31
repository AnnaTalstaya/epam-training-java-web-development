package by.talstaya.task01.reader;

import by.talstaya.task01.exception.CustomException;
import by.talstaya.task01.validator.FileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {

    private static final Logger LOGGER = LogManager.getLogger("name");

    public List<String> read(final File inputFile) throws CustomException {
        List<String> resultOfReading = new ArrayList<>();
        FileValidator fileValidator = new FileValidator();

        if (fileValidator.isValid(inputFile)) {
            try (Stream<String> linesText = Files.lines((Paths.
                    get(inputFile.getPath())))) {
                linesText.forEach(resultOfReading::add);
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, e.getStackTrace(), e);
            }
            return resultOfReading;
        } else {
            throw new CustomException("File doesn't exist");
        }
    }
}
