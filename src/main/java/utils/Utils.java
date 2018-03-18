package utils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {

    public static String EMPTY_STRING = "";
    public static String SPACE_STRING = " ";
    public static String ASTERISK_STRING = "*";
    public static char SPACE_CHAR = ' ';
    public static char ASTERISK_CHAR = '*';

    /**
     * Trim string if not null, else return empty
     *
     * @param s string for trim
     * @return trimmed string
     */
    public static String trimToEmpty(final String s) {
        return Optional.ofNullable(s).orElse("").trim();
    }

    /**
     * Trim string and add space after if exists
     *
     * @param str string for trim
     * @return trimmed string
     */
    public static String trimString(final String str) {
        final String trimmedString = trimToEmpty(str);
        if (hasSpaceInEnd(Optional.ofNullable(str).orElse(""))) {
            return trimmedString + " ";
        }
        return trimmedString;
    }

    /**
     * Has space in the end of string
     *
     * @param str string
     * @return true - has space / false - else
     */
    public static boolean hasSpaceInEnd(final String str) {
        if (Objects.nonNull(str)) {
            final int length = str.length();
            return length > 0 && Objects.equals(str.substring(length - 1), SPACE_STRING);
        }
        return false;
    }

    /**
     * Has asterisk character
     *
     * @param str string
     * @return true - has asterisk / false - else
     */
    public static boolean hasAsteriskChar(final String str) {
        return Objects.nonNull(str) && str.contains(ASTERISK_STRING);
    }

    /**
     * Get class name without package name
     *
     * @param str class name with package name {example a.b.className}
     * @return class name without package name
     */
    public static String getClassName(final String str) {
        if (Optional.ofNullable(str).orElse("").contains(".")) {
            final int index = str.indexOf(".");
            return getClassName(str.substring(index + 1));
        }
        return str;
    }

    /**
     * Make list of Strings to friendly readable string
     *
     * @param listOfString list of string
     * @return friendly readable format string
     */
    public static String makeFriendlySortedReadableString(final List<String> listOfString) {
        if (Objects.nonNull(listOfString)) {
            return String.join("\n", listOfString.stream().sorted(Comparator.comparing(Utils::getClassName)).collect(Collectors.toList()));
        }
        return EMPTY_STRING;
    }
}
