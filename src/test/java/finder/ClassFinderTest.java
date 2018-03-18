package finder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static utils.Utils.EMPTY_STRING;

public class ClassFinderTest {
    private ClassFinder cf;

    @Before
    public void before() {
        cf = new ClassFinder();
    }

//getResourcePath
    @Test(expected = NoSuchFileException.class)
    public void getResourcePath_testNull() throws Exception {
        cf.getResourcePath(null);
    }

    @Test(expected = NoSuchFileException.class)
    public void getResourcePath_testNotExistsName() throws Exception {
        cf.getResourcePath("lalala.txt");
    }

    @Test
    public void getResourcePath_testExistsName() throws Exception {
        final String expectedVal = getClass().getClassLoader().getResource("classes.txt").getPath();
        final String actualVal = cf.getResourcePath("classes.txt");

        Assert.assertEquals(expectedVal, actualVal);
    }

//findClassesByName
    @Test(expected = NoSuchFileException.class)
    public void findClassesByName_testNull() throws Exception {
        cf.findClassesByName(null, null);
    }

    @Test
    public void findClassesByName_testClassExistStringNull() throws Exception {
        final List<String> expectedVal = readAllLines(get(cf.getResourcePath("classes.txt")))
                .stream()
                .filter(line -> !Objects.equals(line, EMPTY_STRING))
                .collect(Collectors.toList());
        final List<String> actualVal = cf.findClassesByName("classes.txt", null);

        Assert.assertArrayEquals(expectedVal.toArray(), actualVal.toArray());
    }
}
