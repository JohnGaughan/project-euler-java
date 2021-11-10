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

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=32">Problem 32</a> asks us to find triplets of numbers where one is the
 * product of the other two, and the numbers are combined pandigital. This means that the numbers 1 through 9 appear
 * once and only once in any of the three numbers.
 * </p>
 * <p>
 * The first thing to consider is ranges. Two one-digit numbers multiplied together will at most produce a two-digit
 * number, which clearly cannot meet the problem's criteria. Considering the number of digits in the multiplicands,
 * there are two numbers of digits possible.
 * </p>
 * <ul>
 * <li>1-digit times a 4-digit produces a 4-digit, total of 9 digits.</li>
 * <li>2-digit times a 3-digit products a 4-digit, total of 9 digits.</li>
 * </ul>
 * <p>
 * This greatly reduces the search space. Furthermore, the exact ranges can be tweaked slightly based on edge values
 * that cannot work (e.g. 10*100=1000 clearly cannot work). After this, churn through the numbers and store them in a
 * hash set for performance and uniqueness. Then add up the values in the set.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "32")
public class Solver_0032
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 45_228;
  }

  @Override
  public long getActualResult() {
    return getPandigitals().stream().mapToInt(Integer::valueOf).sum();
  }

  private Set<Integer> getPandigitals() {
    final Set<Integer> results = new HashSet<>();
    // 1 + 4 + 4
    for (int a = 2; a < 10; ++a) {
      for (int b = 1_234; b <= 9_876; ++b) {
        final int c = a * b;
        if (c < 9876 && areCombinedPandigital(a, b, c)) {
          results.add(Integer.valueOf(c));
        }
      }
    }
    // 2 + 3 + 4
    for (int a = 12; a < 99; ++a) {
      for (int b = 123; b <= 987; ++b) {
        final int c = a * b;
        if (c < 9_876 && areCombinedPandigital(a, b, c)) {
          results.add(Integer.valueOf(c));
        }
      }
    }
    return results;
  }

  private boolean areCombinedPandigital(final int a, final int b, final int c) {
    int x = a;
    // a is one digit. b and c are four digits.
    if (a < 10) {
      x *= 10_000;
      x += b;
      x *= 10_000;
      x += c;
    }
    // a is two digits. b is three digits, c is four digits.
    else {
      x *= 1_000;
      x += b;
      x *= 10_000;
      x += c;
    }
    final int[] digits = new int[9];
    while (x > 0) {
      final int unit = x % 10;
      if (unit == 0) {
        break;
      }
      ++digits[unit - 1];
      x /= 10;
    }
    for (final int digit : digits) {
      if (digit != 1) {
        return false;
      }
    }
    return true;
  }

}
