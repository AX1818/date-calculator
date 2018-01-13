package com.qantas.exercise;

public class DateCalculator {
  private static final int[] Days_Of_Months_In_Leap_Year = {366, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  private static final int[] Days_Of_Months_In_Normal_Year = {365, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public static boolean isLeap(int year) {
    return (year % 4 == 0 && year % 100 > 0) || year % 400 == 0;
  }

  public static int totalDaysByYear(int year) {
    return isLeap(year) ? Days_Of_Months_In_Leap_Year[0] : Days_Of_Months_In_Normal_Year[0];
  }

  public static int passedDaysInYear(int year, int month, int day) {
    int[] monthDays = isLeap(year) ? Days_Of_Months_In_Leap_Year : Days_Of_Months_In_Normal_Year;
    int totalDays = day - 1;
    for (int start = 1; start < month; start++) {
      totalDays += monthDays[start];
    }

    return totalDays;
  }

  public static int leftOverDaysInYear(int year, int month, int day) {
    return totalDaysByYear(year) - passedDaysInYear(year, month, day) - 1;
  }

  public static long daysInBetween(int year, int month, int day, int year2, int month2, int day2) {
    if (isAfter(year, month, day, year2, month2, day2)) {
      return -1 * daysInBetweenAscendDates(year2, month2, day2, year, month, day);
    }

    return daysInBetweenAscendDates(year, month, day, year2, month2, day2);
  }

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
      for (int my = year + 1; my < year2; my++) {
        gapDays += totalDaysByYear(my);
      }
      gapDays += leftOverDaysInYear(year, month, day) + passedDaysInYear(year2, month2, day2);
    }

    return gapDays;
  }

  public static boolean isAfter(int year, int month, int day, int year2, int month2, int day2) {
    return (year * 10000 + month * 100 + day) > (year2 * 10000 + month2 * 100 + day2);
  }
}
