package by.talstaya.task03.model;

import by.talstaya.task03.exception.CustomException;
import by.talstaya.task03.reader.DataReader;
import by.talstaya.task03.validator.MatrixFileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class MatrixCreator {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private String matrixPath = "data/matrix.txt";
    private MatrixFileValidator matrixFileValidator = new MatrixFileValidator();

    public Cell[][] createMatrix() {
        Cell[][] matrix;
        try {
            List<String> linesFromMatrixFile = new DataReader().read(new File(matrixPath));
            if (matrixFileValidator.isValid(linesFromMatrixFile)) {
                matrix = new Cell[linesFromMatrixFile.size()][linesFromMatrixFile.get(0).split("\\s").length];

                for (int i = 0; i < matrix.length; i++) {
                    String[] values = linesFromMatrixFile.get(i).split("\\s");
                    for (int j = 0; j < matrix[i].length; j++) {
                        matrix[i][j] = new Cell(Integer.parseInt(values[j]));
                    }
                }

            } else {
                LOGGER.fatal("Matrix file is invalid");
                throw new RuntimeException("Matrix file is invalid");
            }
        } catch (CustomException e) {
            LOGGER.fatal(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return matrix;
    }
}
