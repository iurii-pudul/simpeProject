package finder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassFinderFiltersTest {

    private ClassFinderFilters cff;

    @Before
    public void before() {
        cff = new ClassFinderFilters();
    }

    @Test
    public void filter_testNull() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter(null, null);

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testEmptyBoth_mustReturnFalse() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("", "");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testDifferentStringsWithDigit() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("123foo", "foo123");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testDifferentStringsWithUpperAndLower() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("FOObar", "FObar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testDifferentStringsWithUpper() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("FOOBAR", "FOBAR");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testDifferentStringsWithLower() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("foobar", "fobar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithLower_match() {
        final boolean expectedVal = true;
        final boolean actualVal = cff.filter("fb", "foobar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithUpper_match() {
        final boolean expectedVal = true;
        final boolean actualVal = cff.filter("FB", "FooBar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testMatchStringsWithUpperAndLower() {
        final boolean expectedVal = true;
        final boolean actualVal = cff.filter("FooB", "FooBar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithUpperAndLowerAndSpaceInTheEnd_match() {
        final boolean expectedVal = true;
        final boolean actualVal = cff.filter("FBar ", "FooBar");
        final boolean actualValFalse = cff.filter("FBar ", "FooBarBar");

        Assert.assertEquals(expectedVal, actualVal);
        Assert.assertEquals(false, actualValFalse);
    }

    @Test
    public void filter_testStringsWithUpperAndLowerAndSpaceInTheEnd_noMatch() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("FooBa ", "FooBar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithUpperAndLowerAndSpaceInTheEnd_noMatchCases() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("fBb ", "FooBarBuzz");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithUpperAndLowerAndSpaceInTheEnd_matchCases() {
        final boolean expectedVal = true;
        final boolean actualVal = cff.filter("FooBar ", "TestFooBar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithUpperAndLowerAndAsterisk_match() {
        final boolean expectedVal = true;
        final boolean actualVal = cff.filter("F**Br", "TestFooBar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithUpperAndLowerAndAsterisk_noMatch() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("F**B**r", "TestFooBar");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithUpperAndAsterisk_match() {
        final boolean expectedVal = true;
        final boolean actualVal = cff.filter("F**", "TestFooBaR");

        Assert.assertEquals(expectedVal, actualVal);
    }

    @Test
    public void filter_testStringsWithUpperAndAsterisk_noMatch() {
        final boolean expectedVal = false;
        final boolean actualVal = cff.filter("F**B", "TestFoBaR");

        Assert.assertEquals(expectedVal, actualVal);
    }

}
