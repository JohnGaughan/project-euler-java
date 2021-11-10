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
 * <a href="https://projecteuler.net/problem=9">Problem 9</a> asks us to find the one Pythagorean triplet whose numbers
 * add up to 1000.
 * </p>
 * <p>
 * This solution uses a basic nested loop algorithm that performs an exhaustive search. It employs a minimal set of
 * optimizations that reduce the search space by a reasonable amount, without muddying the readability of the code.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "9")
public class Solver_0009
implements ISolver {

  private static final int SUM = 1000;

  @Override
  public long getExpectedResult() {
    return 31_875_000;
  }

  @Override
  public long getActualResult() {
    for (int a = 1; a < 995; ++a) {
      for (int b = a + 1; b < (SUM - a) >> 1; ++b) {
        final int c = (SUM - a) - b;
        final int left = (a * a) + (b * b);
        final int right = c * c;
        if (left > right) {
          break;
        }
        else if (left == right) {
          final int product = a * b * c;
          return product;
        }
      }
    }
    return Integer.MIN_VALUE;
  }

}
