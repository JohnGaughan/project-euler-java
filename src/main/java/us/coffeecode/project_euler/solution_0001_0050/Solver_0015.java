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

import java.util.stream.LongStream;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=15">Problem 15</a> asks us to find the number of routes through a 20x20
 * grid from one corner to the opposite corner.
 * </p>
 * <p>
 * This grid is a <a href="https://en.wikipedia.org/wiki/Lattice_path">lattice path</a>. Counting them the long way is a
 * bit tedious: instead, we can use binomial coefficients to calculate them a bit more efficiently.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "15")
public class Solver_0015
implements ISolver {

  /** The input is the grid size. */
  private static final long INPUT = 20;

  @Override
  public long getExpectedResult() {
    return 137_846_528_820L;
  }

  @Override
  public long getActualResult() {
    final long doubled = INPUT << 1;
    return LongStream.rangeClosed(1, INPUT).reduce(1, (x, y) -> x * (doubled + 1 - y) / y);
  }

}
