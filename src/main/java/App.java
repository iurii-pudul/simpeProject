import finder.ClassFinder;

import java.io.IOException;
import java.util.List;

import static utils.Utils.makeFriendlySortedReadableString;


public class App {

    public static void main(final String[] args) throws IOException {
        final ClassFinder classFinder = new ClassFinder();
        final List<String> foundClasses = classFinder.findClassesByName(args[0], "");
        System.out.println(makeFriendlySortedReadableString(foundClasses));
    }

}
