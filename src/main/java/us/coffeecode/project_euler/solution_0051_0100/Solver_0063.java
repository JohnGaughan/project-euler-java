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

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=63">Problem 63</a> asks us to find how many numbers have <code>n</code>
 * digits are are also an <code>nth</code> power of another number.
 * </p>
 * <p>
 * The key to this problem, like several others, is finding the upper bound of the search. At some point, the number
 * being generated will have fewer digits than anything raised to the power. That happens to be when n=10. From here we
 * need to evaluate a limit, iteratively getting closer until the lower bound is greater than the upper bound, at which
 * point there are no more solutions.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "63")
public class Solver_0063
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 49;
  }

  @Override
  public long getActualResult() {
    final int upperBound = 10;
    int result = 0;
    int lowerBound = 0;
    int x = 1;
    while (lowerBound < upperBound) {
      lowerBound = (int) Math.ceil(Math.pow(upperBound, (x - 1.0d) / x));
      result = result + upperBound - lowerBound;
      ++x;
    }
    return result;
  }

}
