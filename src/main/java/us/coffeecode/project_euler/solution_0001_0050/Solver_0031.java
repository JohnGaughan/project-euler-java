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
 * <a href="https://projecteuler.net/problem=31">Problem 31</a> asks us to find the number of combinations possible to
 * add up to £2 using UK currency.
 * </p>
 * <p>
 * This is a simple dynamic programming solution that builds up the number of combinations for each weight and each
 * total, finding the number of new combinations at each step. It ends up being able to skip a lot of redundant
 * combinations, ending up with a single answer for the total weight in the end.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "31")
public class Solver_0031
implements ISolver {

  private static final int[] WEIGHTS = new int[] { 1, 2, 5, 10, 20, 50, 100, 200 };

  private static final int TOTAL = 200;

  @Override
  public long getExpectedResult() {
    return 73_682;
  }

  @Override
  public long getActualResult() {
    final int[] combinations = new int[TOTAL + 1];
    combinations[0] = 1;
    for (final int weight : WEIGHTS) {
      for (int i = weight; i <= TOTAL; ++i) {
        combinations[i] += combinations[i - weight];
      }
    }
    return combinations[combinations.length - 1];
  }

}
