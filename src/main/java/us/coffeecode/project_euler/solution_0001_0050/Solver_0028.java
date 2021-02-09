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
 * <a href="https://projecteuler.net/problem=28">Problem 28</a> asks us to multiply the numbers along the diagonals of a
 * grid.
 * </p>
 * <p>
 * The problem describes a sequence. There is no need to construct the grid since we know the rules that govern it. In
 * fact we can build a recurrence relation to describe the progression of the sum. Where n is the length of a side of a
 * square:
 * </p>
 * <ul>
 * <li><code>f(1) = 1</code></li>
 * <li><code>f(n) = f(n-2) + 4n^2 - 6(n-1)</code>, n > 1 and n is odd</li>
 * </ul>
 * <p>
 * From here we can convert to a polynomial, but it isn't really worth the effort. The recurrence is easily expressed in
 * a simple loop and computes in around a millisecond.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "28")
public class Solver_0028
implements ISolver {

  private static final int LIMIT = 1_001;

  @Override
  public long getExpectedResult() {
    return 669_171_001;
  }

  @Override
  public long getActualResult() {
    // Start with f(1).
    int result = 1;

    // Compute f(n) and add it in.
    for (int i = 3; i <= LIMIT; i += 2) {
      result += ((i * i) << 2) - (6 * i) + 6;
    }

    return result;
  }

}
