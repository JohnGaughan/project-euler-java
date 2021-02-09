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
 * <a href="https://projecteuler.net/problem=45">Problem 45</a> asks us to find the next number greater than 40755 that
 * is triangular, pentagonal, and hexagonal.
 * </p>
 * <p>
 * Since we have the sequence values, we can increment one over what is in the problem as a start. Furthermore,
 * hexagonal numbers are also triangular, so there is no need to check that. The algorithm starts by calculating the
 * next sequence values then looping until they are equal. Whichever one is lower, increment it then check again.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "45")
public class Solver_0045
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 1_533_776_805;
  }

  @Override
  public long getActualResult() {
    // Do not need to check triangle numbers. Every hexagonal number is also triangular.
    // Start at the next two numbers above the ones in the problem. If equal, we found the next number. Otherwise,
    // increment whichever one is smaller and check again.
    long seqPent = 166;
    long seqHex = 144;
    long valPent = pent(seqPent);
    long valHex = hex(seqHex);
    while (valPent != valHex) {
      if (valPent < valHex) {
        valPent = pent(++seqPent);
      }
      else if (valHex < valPent) {
        valHex = hex(++seqHex);
      }
    }
    return valPent;
  }

  private long pent(final long n) {
    return n * (3 * n - 1) / 2;
  }

  private long hex(final long n) {
    return n * (2 * n - 1);
  }

}
