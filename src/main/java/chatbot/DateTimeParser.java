package chatbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

  private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
  private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
  private static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

  public static LocalDateTime parseDateTime (String input) {
    try {
      return LocalDateTime.parse(input, DATETIME_FORMAT);
    } catch (Exception i) {
      try {
        LocalDate dateOnly = LocalDate.parse(input, DATE_FORMAT);
        return LocalDateTime.of(dateOnly, LocalTime.of(10, 0)); // Java needs random time value to convert into format.
      } catch (Exception e) {
        throw new IllegalArgumentException("Error in parsing Date. Use 'd/M/yyyy HHmm' or 'd/M/yyyy'.");
      }
    }
  }

  public static String stringDateTime(LocalDateTime dateTime) {
    if (dateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
      return dateTime.format(DATE_FORMAT); 
    } else {
      return dateTime.format(DATETIME_FORMAT); 
    }
  }

  public static String formatDateTime(LocalDateTime dateTime) {
    if (dateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
      return dateTime.format(OUTPUT_DATE_FORMAT); 
    } else {
      return dateTime.format(OUTPUT_DATETIME_FORMAT); 
    }
  }

}
