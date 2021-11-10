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
 * <a href="https://projecteuler.net/problem=14">Problem 14</a> asks us to enumerate the Collatz sequence and find the
 * longest chain from the starting number to one, given the starting number is under 1,000,000.
 * </p>
 * <p>
 * This is the largest value under a million in sequence <a href="https://oeis.org/A006877">A006877</a>. However, this
 * program calculates it using a simple memoization algorithm instead of using the easy way out.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "14")
public class Solver_0014
implements ISolver {

  /** Number of values to search. */
  private static final int NUMBERS_TO_SEARCH = 1_000_000;

  @Override
  public long getExpectedResult() {
    return 837_799;
  }

  @Override
  public long getActualResult() {
    final int[] cache = new int[NUMBERS_TO_SEARCH];
    int longestChain = Integer.MIN_VALUE;
    int answer = Integer.MIN_VALUE;

    // Ignore 1, which is not the answer anyway.
    for (int i = 2; i < cache.length; ++i) {
      int chainLength = 0;
      long value = i;
      while (value >= i) {
        ++chainLength;
        if (value % 2 == 0) {
          value >>= 1;
        }
        else {
          value = (3 * value) + 1;
        }
      }
      cache[i] = chainLength + cache[(int) value];
      if (cache[i] > longestChain) {
        longestChain = cache[i];
        answer = i;
      }
    }
    return answer;

  }

}
