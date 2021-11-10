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

import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=36">Problem 36</a> asks us to the sum of all numbers less than one million
 * that are palindromic in both bases 2 and 10.
 * </p>
 * <p>
 * Since leading zeros are not possible, anything that ends with zero is not an option. In base 2, this is all even
 * numbers. That right there cuts the search space in half. This also covers decimal numbers that end in 0, which are a
 * subset of those numbers filtered by the even test.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "36")
public class Solver_0036
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 872_187;
  }

  @Override
  public long getActualResult() {
    return IntStream.iterate(1, i -> (i <= 1_000_000), i -> (i + 2)).filter(
      i -> isPalindrome(i, 2) && isPalindrome(i, 10)).sum();
  }

  private boolean isPalindrome(final int value, final int base) {
    int v = value;
    int r = 0;
    while (v > 0) {
      r *= base;
      r += v % base;
      v /= base;
    }
    return r == value;
  }

}
