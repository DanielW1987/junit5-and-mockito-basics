package rocks.danielw.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {

  public static String join(Iterable<?> values, char separator) {
    List<String> strings = new ArrayList<>();
    values.forEach(value -> strings.add(value.toString()));
    return joinList(strings, separator);
  }

  public static String join(Iterator<?> values, char separator) {
    List<String> strings = new ArrayList<>();
    values.forEachRemaining(value -> strings.add(value.toString()));
    return joinList(strings, separator);
  }

  public static String join(Object[] values, char separator) {
    return Stream.of(values).map(Object::toString).collect(Collectors.joining(Character.toString(separator)));
  }

  private static String joinList(List<String> values, char separator) {
    return values.stream().collect(Collectors.joining(Character.toString(separator)));
  }

  public static boolean startsWithCapitalLetter(String value) {
    if (value != null && value.length() >= 1) {
      return value.matches("^[A-Z].*");
    }

    return false;
  }

}
