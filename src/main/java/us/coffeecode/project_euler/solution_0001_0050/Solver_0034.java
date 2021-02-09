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
 * <a href="https://projecteuler.net/problem=34">Problem 34</a> asks us to find the sum of all numbers which are equal
 * to the sum of the factorials of their digits.
 * </p>
 * <p>
 * There first thing that must be done is establish an upper bound, otherwise the program will never halt. While
 * factorials get big quickly, they are manageable up to 9! If we consider the number 999,999, its sum is a 7 digit
 * number. If we then look at 9,999,999, its factorial sum is a smaller 9 digit number. Therefore, <code>7*9!</code> is
 * a reasonable upper bound. However, analyzing the possible sums a bit more reduces the upper bound to be
 * <code>2*8!</code> using more math than with which I care to pollute this class header. The short version is that many
 * nines are impossible to replicate with addition, while eights are not.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "34")
public class Solver_0034
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 40_730;
  }

  @Override
  public long getActualResult() {
    // Cache the factorials 0-9.
    final int[] factorial = new int[10];
    factorial[0] = 1;
    for (int i = 1; i < factorial.length; ++i) {
      factorial[i] = i * factorial[i - 1];
    }

    // Calculate the answer. 564,480 = 8!2
    final int limit = 2 * factorial[factorial.length - 2];
    int result = 0;
    for (int i = 3; i < limit; ++i) {
      int x = i;
      int acc = 0;

      // For each digit, gets its factorial and add it into acc.
      while (x > 0) {
        acc += factorial[x % 10];
        x /= 10;
      }

      // If the sum equals the number, count it.
      if (acc == i) {
        result += acc;
      }
    }
    return result;
  }

}
