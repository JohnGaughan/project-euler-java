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

import static java.math.BigInteger.ONE;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=16">Problem 16</a> asks us to find the sum of the digits of a very large
 * number.
 * </p>
 * <p>
 * Using BigInteger math, getting the number is easy. From there, convert to a string and use a stream operation to sum
 * up all the digits.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "16")
public class Solver_0016
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 1_366;
  }

  @Override
  public long getActualResult() {
    return ONE.shiftLeft(1_000).toString().codePoints().map(c -> c - '0').sum();
  }

}
