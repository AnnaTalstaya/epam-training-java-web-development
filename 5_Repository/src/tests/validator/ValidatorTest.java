package tests.validator;

import by.talstaya.task01.creator.DeveloperCreator;
import by.talstaya.task01.creator.ManagerCreator;
import by.talstaya.task01.validator.FileValidator;
import by.talstaya.task01.validator.LineValidator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidatorTest {
    private DeveloperCreator developerCreator = new DeveloperCreator();
    private ManagerCreator managerCreator = new ManagerCreator();

    private FileValidator fileValidator = new FileValidator();
    private LineValidator lineValidator = new LineValidator();

    @DataProvider
    public Object[][] testDeveloperValidator() {
        return new Object[][]{

        };
    }

    @DataProvider
    public Object[][] testLine() {
        return new Object[][]{
                {Stream.of("Boss", "Jack", "Peru", "3.5",
                        "13/07/2015", "Hi2019s").
                        collect(Collectors.toList()), false},
                {Stream.of("Developer", "Anna", "Role", "3a",
                        "12/01/2018", "JUNIOR").
                        collect(Collectors.toList()), false},
                {Stream.of("Manager", "Maksim", "Pau", "3.5",
                        "13/13/2015", "Hi2019s").
                        collect(Collectors.toList()), false},
                {Stream.of("Manager", "Artur", "Noit", "3.5",
                        "13/07/2015", "Hi2019s").
                        collect(Collectors.toList()), true},
                {Stream.of("Developer", "Olga", "Nana", "4",
                        "10/01/2019", "JUNIOR").
                        collect(Collectors.toList()), true}
        };
    }

    @DataProvider
    public Object[][] testFile() {
        return new Object[][]{
                {new File("data\\input.txt"), true},
                {new File("qwerty"), false},
        };
    }

    @Test(dataProvider = "testFile")
    public void testFileValidator(final File file,
                                  final boolean isValid) {
        Assert.assertEquals(fileValidator.isValid(file), isValid);
    }

    @Test(dataProvider = "testLine")
    public void testLineValidator(final List<String> lines,
                                  final boolean isValid) {
        Assert.assertEquals(isValid, lineValidator.validLine(lines));
    }
}
