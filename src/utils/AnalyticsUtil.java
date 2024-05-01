package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AnalyticsUtil {

  static Logger logger = Logger.getLogger(AnalyticsUtil.class.getName());

  private AnalyticsUtil() {
  }

  public static void generateAnalytic(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      var words = WordUtil.getWords(reader);

      var result = Arrays.stream(words)
          .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
          .entrySet().stream()
          .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
          .limit(30)
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

      result.forEach((key, value) -> logger.info(String.format("%s: %s%n", key, value)));
    } catch (IOException e) {
      throw new RuntimeException(
          "Error reading file from file path: '%s'. Error: '%s'".formatted(filePath, e.getMessage()));
    }
  }
}
