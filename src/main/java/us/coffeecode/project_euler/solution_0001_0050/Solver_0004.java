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
 * <a href="https://projecteuler.net/problem=4">Problem 4</a> asks us to find the largest palindrome number (in base 10)
 * that is the product of two 3-digit numbers.
 * </p>
 * <p>
 * Simply iterate one number from 100 to 998, the other from the first number plus one up to 999. Check the product to
 * see if it is a palindrome: if so, take the maximum of the current largest number and the one just found. This will
 * not duplicate any effort. To check for a palindrome, move decimal digits from one int to another and see if the end
 * result is the same as the original number.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "4")
public class Solver_0004
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 906_609;
  }

  @Override
  public long getActualResult() {
    int max = Integer.MIN_VALUE;
    for (int x = 100; x < 999; ++x) {
      for (int y = x; y < 1000; ++y) {
        final int candidate = x * y;
        if (isPalindrome(candidate)) {
          max = Math.max(max, candidate);
        }
      }
    }
    return max;
  }

  private boolean isPalindrome(final int number) {
    int value = number;
    int acc = 0;
    while (value > 0) {
      acc = acc * 10 + value % 10;
      value /= 10;
    }
    return acc == number;
  }

}
