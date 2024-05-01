package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class WordUtil {

  private WordUtil() {
  }

  public static String[] getWords(BufferedReader reader) throws IOException {
    return reader.lines()
        .flatMap(line -> Arrays.stream(cleanText(line).split("\\s+")))
        .toArray(String[]::new);
  }

  private static String cleanText(String text) {
    return text.replaceAll("[^A-Za-z ]", " ").toLowerCase(Locale.ROOT);
  }
}
