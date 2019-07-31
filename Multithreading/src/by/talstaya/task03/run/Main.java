package by.talstaya.task03.run;

import by.talstaya.task03.exception.CustomException;
import by.talstaya.task03.model.Matrix;
import by.talstaya.task03.reader.DataReader;
import by.talstaya.task03.thread.DiagonalThread;
import by.talstaya.task03.validator.ThreadFileValidator;
import by.talstaya.task03.writer.DataWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger("name");

    public static void main(String[] args) {

        DataReader dataReader = new DataReader();
        ThreadFileValidator threadFileValidator = new ThreadFileValidator();

        try {
            List<String> threadsValues = dataReader.read(new File("data/threadData.txt"));
            if(threadFileValidator.isValid(threadsValues)) {
                Matrix matrix = Matrix.getInstance();

                LOGGER.info("Matrix was initialized");

                List<DiagonalThread> threads = new ArrayList<>();
                for (int i = 0; i < threadsValues.size(); i++){
                    threads.add(new DiagonalThread(Integer.parseInt(threadsValues.get(i))));
                }

                ExecutorService executorService = Executors.newFixedThreadPool(threadsValues.size());
                threads.forEach(executorService::execute);

                executorService.shutdown();
                executorService.awaitTermination(1, TimeUnit.SECONDS);

                DataWriter dataWriter = new DataWriter(new File("data/result.txt"));
                dataWriter.writeMatrix(matrix);

            } else {
                LOGGER.error("File is invalid");
            }
        } catch (InterruptedException | CustomException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
