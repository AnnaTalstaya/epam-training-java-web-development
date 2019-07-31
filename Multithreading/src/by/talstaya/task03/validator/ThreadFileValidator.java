package by.talstaya.task03.validator;

import java.util.List;

public class ThreadFileValidator {

    private final int MIN_NUMBER_THREADS = 4;
    private final int MAX_NUMBER_THREADS = 6;

    private final String REGEX_NUMBER = "[1-9]+";

    public boolean isValid(final List<String> lines) {
        return lines.size() >= MIN_NUMBER_THREADS
                && lines.size() <= MAX_NUMBER_THREADS
                && lines.stream()
                .allMatch(line -> line.matches(REGEX_NUMBER));
    }
}
