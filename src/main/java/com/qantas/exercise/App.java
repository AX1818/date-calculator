package com.qantas.exercise;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The entry of the command line tool.
 */
public class App {
  public static void main(String[] args) {
    try {
      if (args.length != 2) {
        // It expects only two date arguments
        throw new IllegalArgumentException("Error! Incorrect number of arguments");
      }

      // stream-process dates arguments by parsing them into arrays of year, month, and day.
      // If the dates are invalid, DateTimeParseException will be thrown.
      List<int[]> dateParts = Arrays.stream(args).map(LocalDate::parse)
          .map(date -> new int[] {date.getYear(), date.getMonthValue(), date.getDayOfMonth()})
          .collect(Collectors.toList());

      // the start date array with the structure: {year, month, day}
      int[] startDate = dateParts.get(0);
      // the end date array with the structure: {year, month, day}
      int[] endDate = dateParts.get(1);
      int year = startDate[0], month = startDate[1], day = startDate[2];
      int year2 = endDate[0], month2 = endDate[1], day2 = endDate[2];
      // Call daysInBetween method of DateCalculator class
      long days = DateCalculator.daysInBetween(year, month, day, year2, month2, day2);
      System.out.printf("%s -- %s: %d days", args[0], args[1], days);
    } catch(IllegalArgumentException | DateTimeParseException e) {
      System.out.println(e.getMessage());
      System.out.println("The arguments should be: yyyy-mm-dd yyyy-mm-dd");
      System.out.println("For example: 2017-02-12  2019-11-21");
      System.exit(1);
    }
  }
}
