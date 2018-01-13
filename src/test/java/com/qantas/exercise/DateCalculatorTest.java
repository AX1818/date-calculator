package com.qantas.exercise;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateCalculatorTest {
  @Test
  public void checkLeapYearTest() {
    int startYear = 1582;
    int endYear = startYear + 30000;
    while (startYear < endYear) {
      assertEquals(Year.isLeap(startYear), DateCalculator.isLeap(startYear));
      startYear++;
    }
  }

  @Test
  public void testGetTotalDaysByLeapYear() {
    int totalDays = DateCalculator.totalDaysByYear(2016);
    assertEquals("Leap year should have 366 days", totalDays, 366);
  }

  @Test
  public void testGetTotalDaysByNormalYear() {
    int totalDays = DateCalculator.totalDaysByYear(2017);
    assertEquals("Non-Leap year should have 365 days", totalDays, 365);
  }

  @Test
  public void testGetPassedDaysInNonLeapYear() {
    int passedDays = DateCalculator.passedDaysInYear(2017, 3, 1);
    assertTrue(LocalDate.of(2017, 3, 1).getDayOfYear() - 1 == passedDays);
  }

  @Test
  public void testGetPassedDaysInLeapYear() {
    int passedDays = DateCalculator.passedDaysInYear(2016, 3, 1);
    assertTrue(LocalDate.of(2016, 3, 1).getDayOfYear() - 1 == passedDays);

    passedDays = DateCalculator.passedDaysInYear(2016, 2, 29);
    assertTrue(LocalDate.of(2016, 2, 29).getDayOfYear() - 1 == passedDays);

    passedDays = DateCalculator.passedDaysInYear(2016, 2, 28);
    assertTrue(LocalDate.of(2016, 2, 28).getDayOfYear() - 1 == passedDays);
  }

  @Test
  public void testLeftOverDaysInLeapYear() {
    int leftOverDays = DateCalculator.leftOverDaysInYear(2016, 3, 1);
    assertTrue((Year.of(2016).length() - LocalDate.of(2016, 3, 1).getDayOfYear()) == leftOverDays);

    leftOverDays = DateCalculator.leftOverDaysInYear(2016, 2, 28);
    assertTrue((Year.of(2016).length() - LocalDate.of(2016, 2, 28).getDayOfYear()) == leftOverDays);
  }

  @Test
  public void testDaysBetweenTwoSameYearDates() {
    long gapDays = DateCalculator.daysInBetween(2017, 2, 2, 2017, 2, 28);
    long gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2017, Month.FEBRUARY, 2),
        LocalDate.of(2017, Month.FEBRUARY, 28)) - 1;
    assertTrue(gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(2017, 2, 1, 2017, 3, 28);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2017, Month.FEBRUARY, 1),
        LocalDate.of(2017, Month.MARCH, 28)) - 1;
    assertTrue(gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(2017, 2, 1, 2017, 4, 30);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2017, Month.FEBRUARY, 1),
        LocalDate.of(2017, Month.APRIL, 30)) - 1;
    assertTrue(gapDays2 == gapDays);
  }

  @Test
  public void testDaysBetweenTwoAdjacentYearDates() {
    long gapDays = DateCalculator.daysInBetween(2017, 2, 2, 2018, 2, 28);
    long gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2017, Month.FEBRUARY, 2),
        LocalDate.of(2018, Month.FEBRUARY, 28)) - 1;
    assertTrue(gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(2016, 1, 1, 2017, 4, 30);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2016, Month.JANUARY, 1),
        LocalDate.of(2017, Month.APRIL, 30)) - 1;
    assertTrue(gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(2017, 12, 30, 2018, 1, 1);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2017, Month.DECEMBER, 30),
        LocalDate.of(2018, Month.JANUARY, 1)) - 1;
    assertTrue(gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(2017, 12, 31, 2018, 1, 1);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2017, Month.DECEMBER, 31),
        LocalDate.of(2018, Month.JANUARY, 1)) - 1;
    assertTrue(gapDays2 == gapDays);
  }

  @Test
  public void testDaysBetweenTwoYearGapDates() {
    long gapDays = DateCalculator.daysInBetween(1983, 8, 3, 1989, 1, 3);
    long gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(1983, Month.AUGUST, 3),
        LocalDate.of(1989, Month.JANUARY, 3)) - 1;
    assertTrue(gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(2016, 1, 1, 2019, 4, 30);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2016, Month.JANUARY, 1),
        LocalDate.of(2019, Month.APRIL, 30)) - 1;
    assertTrue(gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(1901, 1, 1, 2999, 12, 31);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(1901, Month.JANUARY, 1),
        LocalDate.of(2999, Month.DECEMBER, 31)) - 1;
    assertTrue(gapDays2 == gapDays);
  }

  @Test
  public void testDaysBetweenTwoSameDates() {
    long gapDays = DateCalculator.daysInBetween(1983, 8, 3, 1983, 8, 3);
    assertTrue(0 == gapDays);
  }

  @Test
  public void testDaysBetweenTwoAdjacentDates() {
    long gapDays = DateCalculator.daysInBetween(1983, 8, 3, 1983, 8, 4);
    assertTrue(0 == gapDays);

    gapDays = DateCalculator.daysInBetween(2018, 1, 31, 2018, 2, 1);
    assertTrue(0 == gapDays);

    gapDays = DateCalculator.daysInBetween(2017, 12, 31, 2018, 1, 1);
    assertTrue(0 == gapDays);
  }

  @Test
  public void testDaysBetweenReversedDates() {
    long gapDays = DateCalculator.daysInBetween(1989, 1, 3, 1983, 8, 3);
    long gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(1983, Month.AUGUST, 3), LocalDate.of(1989, Month.JANUARY, 3)) - 1;
    assertTrue(-1 * gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(2019, 4, 30, 2016, 1, 1);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2019, Month.APRIL, 30)) - 1;
    assertTrue(-1 * gapDays2 == gapDays);

    gapDays = DateCalculator.daysInBetween(2999, 12, 31, 1901, 1, 1);
    gapDays2 = ChronoUnit.DAYS.between(LocalDate.of(1901, Month.JANUARY, 1), LocalDate.of(2999, Month.DECEMBER, 31)) - 1;
    assertTrue(-1 * gapDays2 == gapDays);
  }

}
