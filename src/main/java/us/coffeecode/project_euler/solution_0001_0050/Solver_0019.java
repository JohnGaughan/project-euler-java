/* Project Euler solutions written by John Gaughan
 * Copyright (C) 2021  John Gaughan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package us.coffeecode.project_euler.solution_0001_0050;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=19">Problem 19</a> asks us to find the number of Sundays that fell on the
 * first of the month during the entire 20th century.
 * </p>
 * <p>
 * There are several ways to approach this problem. First is using lookup tables, such as querying Java's date functions
 * (which may not be lookup tables behind the scenes but they present that interface). Second is using Gauss's algorithm
 * or a related algorithm, and adapting it to provide the data needed.
 * </p>
 * <p>
 * I opted for a method that neither looks anything up nor directly calculates anything. Instead, start with the
 * information provided in the problem, and do the math to check each month. Add the number of days in each month and
 * get its residue modulo 7 to get the day of week of the first of the next month. Repeat this until the end date is
 * reached.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "19")
public class Solver_0019
implements ISolver {

  private static final int YEAR_START = 1900;

  private static final int YEAR_END = 2000;

  private static final int MONTH_START = 1;

  private static final int MONTH_END = 12;

  private static final int SUNDAY = 0;

  @Override
  public long getExpectedResult() {
    return 171;
  }

  @Override
  public long getActualResult() {
    // Start on Monday.
    int dayOfWeek = SUNDAY + 1;
    int sundays = 0;
    for (int year = YEAR_START; year <= YEAR_END; ++year) {
      for (int month = MONTH_START; month <= MONTH_END; ++month) {
        // We need to increment through 1900 but not count any Sundays.
        if ((dayOfWeek == SUNDAY) && (year != YEAR_START)) {
          ++sundays;
        }
        dayOfWeek += getNumberOfDays(year, month);
        dayOfWeek %= 7;
      }
    }
    return sundays;
  }

  private int getNumberOfDays(final int year, final int month) {
    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        return 31;
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      case 2:
        // Leap year is every four years unless the year is divisible by 100 unless it is also divisible by 400.
        if (year % 400 == 0) {
          return 29;
        }
        else if (year % 100 == 0) {
          return 28;
        }
        else if (year % 4 == 0) {
          return 29;
        }
        else {
          return 28;
        }
      default:
        throw new IllegalArgumentException("Invalid month [" + month + "]");
    }
  }

}
