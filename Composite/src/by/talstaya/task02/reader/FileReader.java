package by.talstaya.task02.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public String readData(final File file) throws IOException {
        byte[] allData = Files.readAllBytes(Paths.get(file.getPath()));
        return new String(allData);
    }
}
