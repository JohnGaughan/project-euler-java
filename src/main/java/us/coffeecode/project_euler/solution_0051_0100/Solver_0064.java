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

import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.google.common.math.IntMath;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=64">Problem 64</a> asks us to find decimal expansions for square roots, and
 * count how many of them have odd periods.
 * </p>
 * <p>
 * Iterate all of the values in the range specified, and calculate their decimal expansion until the dividend and
 * remainder repeat. Then see if the length is odd.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "64")
public class Solver_0064
implements ISolver {

  private static final int LIMIT = 10_000;

  @Override
  public long getExpectedResult() {
    return 1322;
  }

  @Override
  public long getActualResult() {
    long result = 0;
    for (int n = 2; n <= LIMIT; ++n) {
      int limit = IntMath.sqrt(n, RoundingMode.DOWN);
      if (limit * limit == n) {
        continue;
      }
      int period = 0;
      int d = 1;
      int m = 0;
      int a = limit;
      do {
        m = (d * a) - m;
        d = (n - m * m) / d;
        a = (limit + m) / d;
        ++period;
      } while (a != (limit << 1));
      if ((period & 1) == 1) {
        ++result;
      }
    }
    return result;
  }

}
