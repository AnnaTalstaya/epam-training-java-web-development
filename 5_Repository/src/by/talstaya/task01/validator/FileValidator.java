package by.talstaya.task01.validator;

import java.io.File;

public class FileValidator {
    public boolean isValid(File file) {
        return file.exists();
    }
}
