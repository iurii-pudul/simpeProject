package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UtilsTest {

    @Before
    public void setUp() {}

//trimIsEmpty
    @Test
    public void trimIsEmpty_testNull() {
        final String expectedVal = "";
        final String actualVal = Utils.trimToEmpty(null);

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void trimIsEmpty_testSpaces() {
        final String expectedVal = "";
        final String actualVal = Utils.trimToEmpty("                 ");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void trimIsEmpty_testString() {
        final String expectedVal = "hello 1 2 3";
        final String actualVal = Utils.trimToEmpty("          hello 1 2 3       ");

        Assert.assertEquals(expectedVal, actualVal);
    }

//trimString
    @Test
    public void trimString_testNull() {
        final String expectedVal = "";
        final String actualVal = Utils.trimString(null);

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test(expected = AssertionError.class)
    public void trimString_testSpaces_failure() {
        final String expectedVal = "";
        final String actualVal = Utils.trimString("                      ");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void trimString_testSpaces_ok() {
        final String expectedVal = " ";
        final String actualVal = Utils.trimString("                      ");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void trimString_testString() {
        final String expectedVal = "hello 1 2 3 ";
        final String actualVal = Utils.trimString("          hello 1 2 3       ");

        Assert.assertEquals(expectedVal, actualVal);
    }

//hasSpaceInEnd
    @Test
    public void hasSpaceInEnd_testNull() {
        final boolean expectedVal = false;
        final boolean actualVal = Utils.hasSpaceInEnd(null);

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void hasSpaceInEnd_testSpaces() {
        final boolean expectedVal = true;
        final boolean actualVal = Utils.hasSpaceInEnd("            ");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void hasSpaceInEnd_testSpacesNotInTheEnd() {
        final boolean expectedVal = false;
        final boolean actualVal = Utils.hasSpaceInEnd("h e l l o");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void hasSpaceInEnd_testSpaceInTheEnd() {
        final boolean expectedVal = true;
        final boolean actualVal = Utils.hasSpaceInEnd("hello ");

        Assert.assertEquals(expectedVal, actualVal);
    }

//hasAsteriskChar
    @Test
    public void hasAsteriskChar_testNull() {
        final boolean expectedVal = false;
        final boolean actualVal = Utils.hasAsteriskChar(null);

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void hasAsteriskChar_testSpaces() {
        final boolean expectedVal = false;
        final boolean actualVal = Utils.hasAsteriskChar("           ");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void hasAsteriskChar_testStringWithoutAsterisk() {
        final boolean expectedVal = false;
        final boolean actualVal = Utils.hasAsteriskChar("hello1234567890!@#$%^&()_,-=");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void hasAsteriskChar_testStringWithAsterisk() {
        final boolean expectedVal = true;
        final boolean actualVal = Utils.hasAsteriskChar("hello1234567890!@#*$%^&()_,-=");

        Assert.assertEquals(expectedVal, actualVal);
    }

//getClassName
    @Test
    public void getClassName_testNull() {
        final String expectedVal = null;
        final String actualVal = Utils.getClassName(null);

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void getClassName_testEmptyString() {
        final String expectedVal = "";
        final String actualVal = Utils.getClassName("");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void getClassName_testSpaces() {
        final String expectedVal = "   ";
        final String actualVal = Utils.getClassName("   ");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void getClassName_testNameWithPackage() {
        final String expectedVal = "ClassName";
        final String actualVal = Utils.getClassName("package.name.ClassName");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void getClassName_testNameWithoutPackage() {
        final String expectedVal = "ClassName";
        final String actualVal = Utils.getClassName("ClassName");

        Assert.assertEquals(expectedVal, actualVal);
    }

//makeFriendlySortedReadableString

    @Test
    public void makeFriendlySortedReadableString_testNull() {
        final String expectedVal = "";
        final String actualVal = Utils.makeFriendlySortedReadableString(null);

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void makeFriendlySortedReadableString_testOk() {
        final List<String> prepareVal = Arrays.asList(
                "codeborne.WishMaker",
                "a.b.FooBarBaz",
                "c.d.FooBar",
                "codeborne.MindReader",
                "TelephoneOperator",
                "ScubaArgentineOperator",
                "       YoureLeavingUsHere",
                "   YouveComeToThisPoint",
                "YourEyesAreSpinningInTheirSockets"
        );
        final String expectedVal =
                "       YoureLeavingUsHere\n" +
                "   YouveComeToThisPoint\n" +
                "c.d.FooBar\n" +
                "a.b.FooBarBaz\n" +
                "codeborne.MindReader\n" +
                "ScubaArgentineOperator\n" +
                "TelephoneOperator\n" +
                "codeborne.WishMaker\n" +
                "YourEyesAreSpinningInTheirSockets";
        final String actualVal = Utils.makeFriendlySortedReadableString(prepareVal);

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void makeFriendlySortedReadableString_testFailure() {
        final List<String> prepareVal = Arrays.asList(
                "codeborne.WishMaker",
                "a.b.FooBarBaz",
                "c.d.FooBar",
                "codeborne.MindReader",
                "TelephoneOperator",
                "ScubaArgentineOperator",
                "YoureLeavingUsHere",
                "YouveComeToThisPoint",
                "YourEyesAreSpinningInTheirSockets"
        );
        final String expectedVal =
                "       YoureLeavingUsHere\n" +
                "   YouveComeToThisPoint\n" +
                "c.d.FooBar\n" +
                "a.b.FooBarBaz\n" +
                "codeborne.MindReader\n" +
                "ScubaArgentineOperator\n" +
                "TelephoneOperator\n" +
                "codeborne.WishMaker\n" +
                "YourEyesAreSpinningInTheirSockets";
        final String actualVal = Utils.makeFriendlySortedReadableString(prepareVal);

        Assert.assertNotEquals(expectedVal, actualVal);
    }
}
