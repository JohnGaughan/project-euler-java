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
 * <a href="https://projecteuler.net/problem=2">Problem 2</a> asks us to find the sum of Fibonacci numbers with even
 * values.
 * </p>
 * <p>
 * Fibonacci problems often require memoization or other techniques to be efficient enough to run in a reasonable amount
 * of time. However, this problem is quite simple and can be calculated by a single iteration through the sequence.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "2")
public class Solver_0002
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 4_613_732;
  }

  @Override
  public long getActualResult() {
    int sum = 0;
    int n0 = 0;
    int n1 = 1;
    while (n1 <= 4_000_000) {
      if (n1 % 2 == 0) {
        sum += n1;
      }
      // Shift n1 -> n0, and calculate a new n1.
      n1 += n0;
      n0 = n1 - n0;
    }
    return sum;
  }

}
