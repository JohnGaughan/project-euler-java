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
package us.coffeecode.project_euler.solution_0051_0100;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.PermutationCheck;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=52">Problem 52</a> asks us to find a number such that if we multiply it by
 * each other numbers from 2 to 6, all of the numbers have the same digits but in a different order.
 * </p>
 * <p>
 * There are a couple of small optimizations for this brute force. The number must begin with 1, otherwise multiplying
 * by six will increase its number of digits. It must have at least three digits, otherwise it cannot have six
 * permutations. The main help here is that it must begin with one: this reduces the potential search space by 90%.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "52")
public class Solver_0052
implements ISolver {

  @Autowired
  private PermutationCheck permutations;

  @Override
  public long getExpectedResult() {
    return 142_857;
  }

  @Override
  public long getActualResult() {
    // Must be at least three digits to have six permutations.
    for (int x = 123; x <= Integer.MAX_VALUE; ++x) {
      // Must begin with 1, else 6x has more digits. So skip to the next power of ten.
      if (firstDigit(x) != 1) {
        x = nextStartValue(x);
      }
      if (permutations.test(x, x * 2) && permutations.test(x, 3 * x) && permutations.test(x, x * 4)
        && permutations.test(x, 5 * x) && permutations.test(x, 6 * x)) {
        return x;
      }
    }
    return -1;
  }

  /** Get the first base 10 digit of the number. */
  private int firstDigit(final int number) {
    int n = number;
    while (n > 9) {
      n /= 10;
    }
    return n;
  }

  /**
   * Given a number with X digits, get the next greatest number with X+1 digits starting with one and increasing. For
   * example, given number 200, the next value will be 1234.
   */
  private int nextStartValue(final int number) {
    int digits = 1;
    for (int n = number; n > 0; n /= 10) {
      ++digits;
    }
    int next = 0;
    for (int i = 1; i <= digits; ++i) {
      next = (next * 10) + i;
    }
    return next;
  }

}
