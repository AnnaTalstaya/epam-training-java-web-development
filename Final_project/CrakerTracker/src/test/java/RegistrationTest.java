import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationTest {

    private final String STRING_REGEX_ALPHABETIC_STRING = "\\p{IsAlphabetic}+";
    private final String STRING_REGEX_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,16}";
    private final String STRING_REGEX_USERNAME = "[A-Za-z0-9_][.A-Za-z0-9_]{2,48}[A-Za-z0-9_]";

    private Pattern regexPassword = Pattern.compile(STRING_REGEX_PASSWORD);
    private Pattern regexUsername = Pattern.compile(STRING_REGEX_USERNAME);
    private Pattern regexAlphabeticString = Pattern.compile(STRING_REGEX_ALPHABETIC_STRING);


    @DataProvider
    public Object[][] testPassword() {
        return new Object[][]{
                {"Aqazwsx1", true},
                {"nksn1Kos", true},
                {"frbaremvpkdrm", false},
                {"eA1", false},
                {"1234523Aa", true},
                {"dsvaE1", false},
                {"A1qazws", true}
        };
    }

    @DataProvider
    public Object[][] testUsername() {
        return new Object[][]{
                {".qwe", false},
                {"", false},
                {"A", false},
                {"Acdf.", false},
                {"_", false},
                {"______", true},
                {"A_sdf.", false},
                {"Asd_fs", true}

        };
    }

    @DataProvider
    public Object[][] testFirstName() {
        return new Object[][]{
                {"Анна", true},
                {"1ва", false},
                {"Adf", true}

        };
    }


    @Test(dataProvider = "testPassword")
    public void validationPasswordTest(String password, boolean expectedResult) {

        Assert.assertEquals(regexPassword.matcher(password).matches(), expectedResult);

    }

    @Test(dataProvider = "testUsername")
    public void validationUsernameTest(String username, boolean expectedResult) {

        Assert.assertEquals(regexUsername.matcher(username).matches(), expectedResult);

    }

    @Test(dataProvider = "testFirstName")
    public void validationFirstNameTest(String firstName, boolean expectedResult) {
        Assert.assertEquals(regexAlphabeticString.matcher(firstName).matches(), expectedResult);

    }

}
