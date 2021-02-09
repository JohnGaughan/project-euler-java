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

import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=1">Problem 1</a> asks us to find all numbers below 1,000 that are multiples
 * of either 3 or 5, and find their sum.
 * </p>
 * <p>
 * It is possible to calculate the answer directly using summations: however, that goes against the spirit of writing a
 * program to find the solution. For that reason, this program uses a simple iterative solution hiding behind Java
 * streams.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "1")
public class Solver_0001
implements ISolver {

  private static final int INPUT = 1_000;

  @Override
  public long getExpectedResult() {
    return 233_168;
  }

  @Override
  public long getActualResult() {
    return IntStream.range(1, INPUT).filter(i -> i % 3 == 0 || i % 5 == 0).sum();
  }

}
