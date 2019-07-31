package by.talstaya.task03.thread;

import by.talstaya.task03.exception.CustomException;
import by.talstaya.task03.model.Cell;
import by.talstaya.task03.model.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiagonalThread extends Thread {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private int number;

    public DiagonalThread(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        Matrix matrix = Matrix.getInstance();

        LOGGER.debug(Thread.currentThread().getName() + " stated");

        int counter = 0;
        while (matrix.size() > counter) {
            try {
                Cell cell = matrix.takeCell(counter, counter);

                if (cell.getValue() != 0) {
                    counter++;
                } else if (cell.getLock().tryLock()) {
                    cell.setValue(number);
                    cell.getLock().unlock();
                } else {
                    counter++;
                }
            } catch (CustomException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        LOGGER.debug(Thread.currentThread().getName() + " finished");

    }
}
