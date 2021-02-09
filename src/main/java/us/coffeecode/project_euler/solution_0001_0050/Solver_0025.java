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

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=25">Problem 25</a> asks us to find the first Fibonacci number that has
 * 1,000 digits, and return its position in the sequence.
 * </p>
 * <p>
 * This is another problem made possible through using BigInteger. Calculate the first natural number with 1,000 digits,
 * then iterate through Fibonacci until we find a value that is equal to or greater than that lower limit. Return the
 * sequence number.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "25")
public class Solver_0025
implements ISolver {

  private static final BigInteger LIMIT = BigInteger.TEN.pow(999);

  @Override
  public long getExpectedResult() {
    return 4_782;
  }

  @Override
  public long getActualResult() {
    BigInteger f1 = BigInteger.ONE;
    BigInteger f2 = f1;
    int sequence = 2;
    while (f2.compareTo(LIMIT) < 0) {
      final BigInteger next = f1.add(f2);
      f1 = f2;
      f2 = next;
      ++sequence;
    }
    return sequence;
  }

}
