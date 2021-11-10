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
 * <a href="https://projecteuler.net/problem=6">Problem 6</a> asks us to find the difference between the sum of squares
 * and square of sum of the first hundred natural numbers.
 * </p>
 * <p>
 * The solution is to plug 100 into the formulas to calculate the two sums, then subtract to get the difference.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "6")
public class Solver_0006
implements ISolver {

  private static final int INPUT = 100;

  @Override
  public long getExpectedResult() {
    return 25_164_150;
  }

  @Override
  public long getActualResult() {
    final int squared = INPUT * INPUT;
    final int cubed = INPUT * squared;
    final int sumOfSquares = ((2 * cubed) + (3 * squared) + INPUT) / 6;

    final int sum = (INPUT + squared) >> 1;
    final int squareOfSum = sum * sum;

    return squareOfSum - sumOfSquares;
  }

}
