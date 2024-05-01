# Key Optimizations

1. Used `BufferedReader` for file reading instead of `Files.readString` to optimize memory usage and improve speed.
2. Changed the `WordUtil.getWords` method to read lines from the file iteratively, avoiding loading the entire file into memory at once.
3. Replaced `LocalDateTime` with `System.nanoTime()` in `MeasureTimeUtil` for more accurate time measurement at a nanosecond precision level.
