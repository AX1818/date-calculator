package com.qantas.exercise;

/**
 * The utility class to provide custom date calculation methods.
 */
public class DateCalculator {
  /**
   * The meta data of days of a leap year
   * The first element is the total days of a leap year which is 366. The following elements are the number of days in
   * every month.
   */
  private static final int[] Days_Of_Months_In_Leap_Year = {366, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  /**
   * The meta data of days of a common year
   * The first element is the total days of a leap year which is 365. The following elements are the number of days in
   * every month.
   */
  private static final int[] Days_Of_Months_In_Normal_Year = {365, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  /**
   * Check if a year is a leap year
   * @param year the year to be checked
   * @return true - a leap year
   *         false - a common year
   */
  public static boolean isLeap(int year) {
    return (year % 4 == 0 && year % 100 > 0) || year % 400 == 0;
  }

  /**
   * Get the total days in a year
   * @param year the year to get the total days from
   * @return the number of total days in the year. 366 - for a leap year and 365 - for a common year
   */
  public static int totalDaysByYear(int year) {
    return isLeap(year) ? Days_Of_Months_In_Leap_Year[0] : Days_Of_Months_In_Normal_Year[0];
  }

  /**
   * The number of days from the start of the year to the exclusive day.
   * @param year the year
   * @param month the month
   * @param day   the day of the month
   * @return the number of the passed days till the day before the specified day.
   */
  public static int passedDaysInYear(int year, int month, int day) {
    int[] monthDays = isLeap(year) ? Days_Of_Months_In_Leap_Year : Days_Of_Months_In_Normal_Year;
    int totalDays = day - 1;
    for (int start = 1; start < month; start++) {
      totalDays += monthDays[start];
    }

    return totalDays;
  }

  /**
   * The remaining days from the day after the specified day to the end of the year
   * @param year the year
   * @param month the month
   * @param day the day of the month
   * @return the remaining days to the end of the year, excluding the specified day.
   */
  public static int leftOverDaysInYear(int year, int month, int day) {
    return totalDaysByYear(year) - passedDaysInYear(year, month, day) - 1;
  }

  /**
   * Calculate the days in between the start and end dates which are exclusive.
   * @param year the start year
   * @param month the start month
   * @param day  the start day of the month
   * @param year2 the end year
   * @param month2 the end month
   * @param day2 the day of the end month
   * @return the days between the two dates which are excluded.
   */
  public static long daysInBetween(int year, int month, int day, int year2, int month2, int day2) {
    if (isAfter(year, month, day, year2, month2, day2)) {
      return -1 * daysInBetweenAscendDates(year2, month2, day2, year, month, day);
    }

    return daysInBetweenAscendDates(year, month, day, year2, month2, day2);
  }

  /**
   * Calculate the days in between the start and end dates which are ascending and exclusive. i.e. The start date must
   * not be after the end date and they are not counted as the in-between days.
   * @param year the start year
   * @param month the start month
   * @param day  the start day of the month
   * @param year2 the end year
   * @param month2 the end month
   * @param day2 the day of the end month
   * @return the days between the two dates which are excluded.
   */
  private static long daysInBetweenAscendDates(int year, int month, int day, int year2, int month2, int day2) {
    int gapDays = 0;
    if (year == year2) {
      // in same year
      gapDays = passedDaysInYear(year2, month2, day2) - passedDaysInYear(year, month, day) - 1;
      if (gapDays == -1) {
        // On Same day
        gapDays = 0;
      }
    } else {
      for (int midYear = year + 1; midYear < year2; midYear++) {
        gapDays += totalDaysByYear(midYear);
      }
      gapDays += leftOverDaysInYear(year, month, day) + passedDaysInYear(year2, month2, day2);
    }

    return gapDays;
  }

  /**
   * Check if the start date is after the end date
   * @param year the start year
   * @param month the start month
   * @param day  the start day of the month
   * @param year2 the end year
   * @param month2 the end month
   * @param day2 the day of the end month
   * @return true - the start date is after the end date
   *         false - otherwise.
   */
  static boolean isAfter(int year, int month, int day, int year2, int month2, int day2) {
    return (year * 10000 + month * 100 + day) > (year2 * 10000 + month2 * 100 + day2);
  }
}
