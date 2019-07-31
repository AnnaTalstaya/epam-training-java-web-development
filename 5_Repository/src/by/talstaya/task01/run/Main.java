package by.talstaya.task01.run;

import by.talstaya.task01.comparator.DeveloperStatusComparator;
import by.talstaya.task01.comparator.EmployeeSalaryComparator;
import by.talstaya.task01.creator.DeveloperCreator;
import by.talstaya.task01.creator.ManagerCreator;
import by.talstaya.task01.entity.Developer;
import by.talstaya.task01.entity.Manager;
import by.talstaya.task01.exception.CustomException;
import by.talstaya.task01.parser.FileParser;
import by.talstaya.task01.reader.FileReader;
import by.talstaya.task01.repository.DeveloperRepository;
import by.talstaya.task01.specification.RaiseTheSalarySpecification;
import by.talstaya.task01.specification.SearchByDeveloperStatusSpecification;
import by.talstaya.task01.specification.SearchBySalaryPerHourSpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger("name");

    public static void main(String[] args) {
        DeveloperCreator developerCreator = new DeveloperCreator();
        ManagerCreator managerCreator = new ManagerCreator();

        FileReader fileReader = new FileReader();
        FileParser fileParser = new FileParser();

        try {
            List<List<String>> lines = fileParser.
                    parseFile(fileReader.read(new File("data/input.txt")));

            List<Developer> developerList = new ArrayList<>();
            List<Manager> managerList = new ArrayList<>();

            for (List<String> line : lines){
                switch (line.get(0)){
                    case "Developer":
                        try {
                            developerList.add((Developer)developerCreator.factoryMethod(line));
                        } catch (CustomException e) {
                            LOGGER.log(Level.ERROR, "Can't add developer: ", e);
                        }
                        break;
                    case "Manager":
                        try {
                            managerList.add((Manager)managerCreator.factoryMethod(line));
                        } catch (CustomException e) {
                            LOGGER.log(Level.ERROR, "Can't add manager: ", e);
                        }
                }

            }

            DeveloperRepository repository = new DeveloperRepository();
            developerList.forEach(repository::add);

            System.out.println();
            System.out.print("Team cost per hour: ");
            BigDecimal sumCost = new BigDecimal(0);
            for (Developer developer: developerList) {
                sumCost = sumCost.add(developer.getSalaryPerHour());
            }
            System.out.println(sumCost);
            System.out.println();

            List<Developer> developerSortedList = repository.sort(new DeveloperStatusComparator().thenComparing(new EmployeeSalaryComparator()));
            System.out.println("Sorted list of developers: ");
            developerSortedList.forEach(System.out::println);
            System.out.println();

            BigDecimal minSal = new BigDecimal(5);
            BigDecimal maxSal = new BigDecimal(10);
            List<Developer> developersWithSalaryRange = repository.query(new SearchBySalaryPerHourSpecification(minSal, maxSal));
            System.out.println("List of developers with salary per hour from " + minSal + " to " + maxSal + ": ");
            developersWithSalaryRange.forEach(System.out::println);
            System.out.println();

            developersWithSalaryRange = repository.query(new SearchBySalaryPerHourSpecification(minSal, maxSal), new RaiseTheSalarySpecification(new BigDecimal(12)));
            System.out.println("List of developers with raised salary: ");
            developersWithSalaryRange.forEach(System.out::println);
            System.out.println();

            Developer.DeveloperStatus status = Developer.DeveloperStatus.MIDDLE;
            List<Developer> developersWithFoundStatus = repository.query(new SearchByDeveloperStatusSpecification(status));
            System.out.println("List of developers with status " + status + ": ");
            developersWithFoundStatus.forEach(System.out::println);
        } catch (CustomException e) {
            LOGGER.log(Level.FATAL, "Can't add manager: ", e);
        }
    }
}
