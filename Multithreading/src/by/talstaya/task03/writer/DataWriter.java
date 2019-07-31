package by.talstaya.task03.writer;

import by.talstaya.task03.exception.CustomException;
import by.talstaya.task03.model.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {
    private static final Logger LOGGER = LogManager.getLogger("name");

    private File outputFile;

    public DataWriter(File outputFile) {
        this.outputFile = outputFile;
    }

    public void writeMatrix(Matrix matrix) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(outputFile.getPath());

            for (int i = 0; i < matrix.size(); i++) {
                for (int j = 0; j < matrix.size(); j++) {
                    try {
                        fw.write(String.valueOf(matrix.takeCell(i, j).getValue()) + " ");
                    } catch (CustomException e) {
                        LOGGER.fatal(e.getMessage(), e);
                    }
                }
                fw.write(System.getProperty("line.separator"));
            }

        } catch (IOException e) {
            LOGGER.fatal(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    LOGGER.fatal(e.getMessage(), e);
                }
            }
        }
    }
}
