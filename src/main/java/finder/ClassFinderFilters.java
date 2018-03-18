package finder;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static utils.Utils.*;

class ClassFinderFilters {

    /**
     * Compare strings considering the filters
     *
     * @param searchString keyword for search
     * @param stringFromFile string from file
     * @return true - suitable / false - else
     */
    boolean filter(final String searchString, final String stringFromFile) {
        return Objects.nonNull(searchString) &&
                Objects.nonNull(stringFromFile) &&
                !Objects.equals(stringFromFile, EMPTY_STRING) &&
                filterByUpperOrLowerCases(searchString, getClassName(stringFromFile));
    }

    /**
     * choose path for filtering
     * has only upper case letters or
     * has only lower case letters or
     * has both cases
     *
     * @param searchString keyword for search
     * @param stringFromFile string from file
     * @return result of filtering
     */
    private boolean filterByUpperOrLowerCases(final String searchString, final String stringFromFile) {
        if (isUpperPath(searchString)) {
            return filterByOrderOfCharacters(getAllUpperLittersFromString(searchString), stringFromFile);
        } else if (isLowerPath(searchString)) {
            return filterByOrderOfCharacters(getAllLowerLittersFromString(searchString), stringFromFile.toLowerCase());
        } else {
            return filterByOrderOfCharacters(searchString, stringFromFile);
        }
    }

    /**
     * filter by order of upper case characters in strings
     *
     * @param searchString keyword for search
     * @param stringFromFile string from file
     * @return true - order is fine / false - else
     */
    private boolean filterByOrderOfCharacters(final String searchString, final String stringFromFile) {
        int indexOfMatch = 0;
        int countOfMatches = 0;
        String substringOfStringFromFile = stringFromFile;
        final boolean hasSpace = hasSpaceInEnd(searchString);
        final boolean hasAsteriskChar = hasAsteriskChar(searchString);
        for (final char charFromStringSearch : searchString.toCharArray()) {
            if (hasSpace && Objects.equals(charFromStringSearch, SPACE_CHAR)) {
                if (isEndOfString(indexOfMatch, substringOfStringFromFile)) {
                    countOfMatches++;
                    break;
                }
                continue;
            }
            if (hasAsteriskChar && Objects.equals(charFromStringSearch, ASTERISK_CHAR)) {
                countOfMatches++;
                indexOfMatch++;
                continue;
            }
            if (substringOfStringFromFile.length() < indexOfMatch) {
                break;
            }
            substringOfStringFromFile = substringOfStringFromFile.substring(indexOfMatch);
            for (final char charFromStringFromFile : substringOfStringFromFile.toCharArray()) {
                if (Objects.equals(charFromStringFromFile, charFromStringSearch)) {
                    indexOfMatch = substringOfStringFromFile.indexOf(charFromStringFromFile);
                    indexOfMatch++;
                    countOfMatches++;
                    break;
                }
            }
        }
        return Objects.equals(countOfMatches, searchString.length());
    }

    private boolean isEndOfString(final int index, final String str) {
        return str.length() >= index && Objects.equals(str.substring(index), EMPTY_STRING);
    }

    /**
     * Get all upper case characters from string
     *
     * @param str string
     * @return string with only upper case characters from str
     */
    private String getAllUpperLittersFromString(final String str) {
        final String result = str.codePoints()
                .filter(letter -> Character.isUpperCase(letter) || Objects.equals(letter, SPACE_CHAR))
                .mapToObj(c -> Character.toString((char) c))
                .collect(Collectors.joining());
        return hasSpaceInEnd(str) ? result + " " : result;
    }

    /**
     * Get all lower case characters from string
     *
     * @param str string
     * @return string with only upper case characters from str
     */
    private String getAllLowerLittersFromString(final String str) {
        final String result = str.codePoints()
                .filter(letter -> Character.isLowerCase(letter) || Objects.equals(letter, SPACE_CHAR))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return hasSpaceInEnd(str) ? result + " " : result;
    }

    /**
     * has only lower case letters without digit
     *
     * @param searchString string for search
     * @return true - only lower/ false - else
     */
    private boolean isLowerPath(final String searchString) {
        return Optional.ofNullable(searchString).orElse("").codePoints().noneMatch(Character::isUpperCase) &&
                hasNotDigit(searchString) &&
                !hasAsteriskChar(searchString);
    }

    /**
     * has only upper case letters without digit
     *
     * @param searchString string for search
     * @return true - only upper/ false - else
     */
    private boolean isUpperPath(final String searchString) {
        return Optional.ofNullable(searchString).orElse("").codePoints().noneMatch(Character::isLowerCase) &&
                hasNotDigit(searchString) &&
                !hasAsteriskChar(searchString);
    }

    /**
     * has no digit letters
     *
     * @param searchString string for search
     * @return true - only not digit letters/ false - else
     */
    private boolean hasNotDigit(String searchString) {
        return Optional.ofNullable(searchString).orElse("").codePoints().noneMatch(Character::isDigit);
    }
}
