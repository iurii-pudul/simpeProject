package finder;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.Optional.ofNullable;
import static utils.Utils.trimString;
import static utils.Utils.trimToEmpty;

/**
 * Search name of classes from file
 */
public class ClassFinder {

    /**
     * Get all contains strings from file by keyword
     *
     * @param fileName name of file
     * @param searchString string with keyword for search
     * @return list of strings from file
     * @throws IOException something went wrong
     */
    public List<String> findClassesByName(final String fileName, final String searchString) throws IOException {
        return readAllLines(get(getResourcePath(fileName)))
                .stream()
                .distinct()
                .filter(stringFromFile ->
                        new ClassFinderFilters().filter(trimString(searchString), trimToEmpty(stringFromFile))
                )
                .collect(Collectors.toList());
    }

    /**
     * Get path to resource file
     *
     * @param fileName name of file
     * @return path to resource file
     * @throws NoSuchFileException file not found
     */
    public String getResourcePath(final String fileName) throws NoSuchFileException {
        final String name = Optional.ofNullable(fileName).orElseThrow(() -> new NoSuchFileException("File with classes not found"));
        return ofNullable(getClass().getClassLoader().getResource(name))
                .orElseThrow(() -> new NoSuchFileException("File with classes not found"))
                .getPath();
    }
}
