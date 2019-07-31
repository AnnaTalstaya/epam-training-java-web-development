package tests.validator;

import by.talstaya.task05.validator.FlowersXMLValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowersXMLValidatorTest {
    private FlowersXMLValidator validator = new FlowersXMLValidator();

    @Test
    public void fileIsCorrect() {
        boolean actual = validator.validFile("data/greenhouse.xml");

        Assert.assertTrue(actual);
    }

    @Test
    public void fileIsIncorrect() {
        boolean actual = validator.validFile("data/greenhouse_incorrect.xml");

        Assert.assertFalse(actual);
    }
}
