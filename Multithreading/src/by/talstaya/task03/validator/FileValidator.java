package by.talstaya.task03.validator;

import java.io.File;

public class FileValidator {
    public boolean isValid(File file) {
        return file.exists();
    }
}
