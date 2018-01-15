# Qantas Date Calculator Challenge

## Solution
My date calculator is designed to be an executable jar which takes two command line date arguments: start date and end date.  
The key of the date calculator is an utility class **`DateCalculator`** with a number static helper methods. It works out the days in between
the two dates using the following algorithm for *`daysInBetween(int year, int month, int day, int year2, int month2, int day2)`*:
 - Two dates in the same year
   ```
   daysInBetween = passedDaysOfEndDateInYear - passedDaysOfStartDateInYear - 1
   ```
   For example, given two dates `2017-01-15` and `2017-02-01`,
   ``` java
   // 31 days for `2017-02-01`
   int passedDaysOfEndDateInYear = DateCalculator.passedDaysInYear(2017, 2, 1); 
   // 14 days `2017-01-15`
   int passedDaysOfStartDateInYear = DateCalculator.passedDaysInYear(2017, 1, 15);
   // dayInBetween = 31 - 14 - 1 = 16 days
   int daysInBetween = passedDaysOfEndDateInYear - passedDaysOfStartDateInYear - 1;
   ``` 
   
 - Two Dates in the different years
   ```
   daysInBetween = leftOverDaysOfStartDateInStartYear + passedDaysOfEndDateInEndYear + totalDaysOfGapYears
   ```
   For example, given two dates `2015-12-15` and `2017-02-01`
   ```
   // 16 days left in year 2015
   int leftOverDaysOfStartDateInStartYear = DateCalculator.leftOverDaysInYear(2015, 12, 15);
   // 31 days passed in year 2017
   int passedDaysOfEndDateInEndYear = DateCalculator.passedDaysInYear(2017, 2, 1);
   // 366 days in the leap year 2016
   int totalDaysOfGapYears = 0;
   // gapsYears = years in [start year + 1, end year - 1], here is 2016 that is a leap year with 366 days
   for (int year = 2015 + 1; year < 2017; year++) {
       totalDaysOfGapYears += DateCalculator.totalDaysByYear(year);
   } 
   // totalDaysOfGapYears = 366 because 2016 is a leap year
   // daysInBetween = 16 + 31 + 366 = 413 days
   daysInBetween = leftOverDaysOfStartDateInStartYear + passedDaysOfEndDateInEndYear + totalDaysOfGapYears;
   ```
   
 ## How to build the 
 This is a maven java 8 project. Therefore, maven and Java 8 are needed to build the project. Then, All you need to do is to run:
  ```
  mvn package
  ``` 
 
 ## How to run the command line tool
 The out of the project build is an executable **`date-calculator-1.0.jar`** which takes two date arguments following the format `yyyy-mm-dd`.  
 
 Note: by default, the jar file is under `target` folder of the checked-out project after building the project.  
 
 For example:  
 Assuming you are at the containing folder of **`date-calculator-1.0.jar`** -- `target` by default.
 ```bash
 java -jar date-calculator-1.0.jar 2015-12-15 2017-02-01
 ```
 the output is:
 ```
 2015-12-15 -- 2017-02-01: 413 days
 ```
 If the start date is after the end date, you will get a negative in-between days:
 ```bash
 java -jar date-calculator-1.0.jar 2017-02-01 2015-12-15
 ```
 and the output is:
 ```
 2017-02-01 -- 2015-12-15: -413 days
 ```
 
 ## How to run tests
 The source code is tested using Junit5. You can run them by run:
 ```bash
 mvn test
 ```
 
 Note: a git push hook can be configured to run the tests automatically when you're trying to commit the changes.
 