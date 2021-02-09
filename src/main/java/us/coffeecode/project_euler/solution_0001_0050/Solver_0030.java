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

import com.google.common.math.IntMath;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=30">Problem 30</a> asks us to find the sum of all numbers that can be
 * written as the sum of fifth powers of their digits.
 * </p>
 * <p>
 * The problem does not supply an upper bound for what the numbers can be. In order for this program to halt at some
 * point, we need to figure out the upper bound. The largest fifth power of any digit is
 * <code>9<sup>5</sup> = 59049</code>. A six digit number with all nines would have a sum of 354,294. A seven digit
 * number with all nines would have a sum of 413,343, which is only six digits: this is impossible. Therefore, I use the
 * smaller upper bound here which does produce the correct answer and takes very little time.
 * </p>
 * <p>
 * The true upper bound based on actual results, that is, the highest number the program reports as meeting the
 * criteria, is 194,980. However, I am not sure how to <i>prove</i> this, so I leave the calculated upper bound in place
 * instead.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "30")
public class Solver_0030
implements ISolver {

  private static final int LIMIT = 354_294;

  @Override
  public long getExpectedResult() {
    return 443_839;
  }

  @Override
  public long getActualResult() {
    int result = 0;
    for (int i = 2; i <= LIMIT; ++i) {
      if (i == sum(i)) {
        result += i;
      }
    }
    return result;
  }

  /** Get the sum of each digit to the fifth power. */
  private int sum(final int number) {
    int n = number;
    int sum = 0;
    do {
      final int digit = n % 10;
      sum += IntMath.pow(digit, 5);
      n /= 10;
    } while (n > 0);
    return sum;
  }

}
