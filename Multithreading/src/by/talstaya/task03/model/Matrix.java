package by.talstaya.task03.model;


import by.talstaya.task03.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static Matrix instance;

    private Cell[][] matrix;

    private static AtomicBoolean create = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();

    private Matrix() {
        init();
    }

    private void init() {
        matrix = (new MatrixCreator()).createMatrix();
    }

    public static Matrix getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new Matrix();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public int size() {
        return matrix.length;
    }

    public Cell takeCell(int i, int j) throws CustomException {
        if (i < matrix.length && j < matrix[0].length)
            return matrix[i][j];
        else throw new CustomException("There is no cell in this matrix with coordinates (" + i + "," + j + ")");
    }

}
