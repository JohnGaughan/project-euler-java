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

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=56">Problem 56</a> asks us to find the maximum sum of digits among all
 * numbers of the form <code>a<sup>b</sup>, a, b &lt; 100</code>.
 * </p>
 * <p>
 * As far as I can tell, there is no mathematical way to <i>prove</i> a way to reduce the search space beyond the
 * trivial cases of powers of ten, even if intuition tells us otherwise. For this reason, the algorithm uses brute
 * force. The only significant time savings was converting BigInteger to string and summing the digits that way. It was
 * around 20% faster than the mathematical method, likely because these BigIntegers get massive and thus inefficient.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "56")
public class Solver_0056
implements ISolver {

  private static final int LIMIT = 100;

  @Override
  public long getExpectedResult() {
    return 972;
  }

  @Override
  public long getActualResult() {
    int result = 0;
    for (int x = 2; x < LIMIT; ++x) {
      if (x == 10) {
        continue;
      }
      for (int y = 2; y < LIMIT; ++y) {
        final BigInteger value = BigInteger.valueOf(x).pow(y);
        final int sum = value.toString().codePoints().map(ch -> ch - '0').sum();
        if (sum > result) {
          result = sum;
        }
      }
    }
    return result;
  }

}
