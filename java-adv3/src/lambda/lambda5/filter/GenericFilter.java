package lambda.lambda5.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GenericFilter {
    public static <T> List<T> filter(List<T> numbers, Predicate<T> predicate) {
        ArrayList<T> filtered = new ArrayList<>();

        for (T number : numbers) {
            if(predicate.test(number)) {
                filtered.add(number);
            }
        }
        return filtered;
    }
}
