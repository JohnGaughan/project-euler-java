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
 * <a href="https://projecteuler.net/problem=48">Problem 48</a> asks us to sum up a series of numbers and return the
 * last ten digits.
 * </p>
 * <p>
 * These numbers do not fit in any Java primitive without overflow or loss of precision. Instead, use modular arithmetic
 * using the identity <code>(a * b) mod m = [(a mod m) * (b mod m)] mod m</code>. Furthermore, that identity simplifies
 * a bit since i never overflows and the result is stored back in the multiplicand before being used again. The only
 * other important thing is to take the modulo every time we add a value in the sequence, otherwise it will overflow.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "48")
public class Solver_0048
implements ISolver {

  private static final long MOD = 10_000_000_000L;

  @Override
  public long getExpectedResult() {
    return 9_110_846_700L;
  }

  @Override
  public long getActualResult() {
    long result = 0;
    for (int i = 1; i <= 1_000; ++i) {
      long value = 1;
      for (int j = 0; j < i; ++j) {
        value = (value * i) % MOD;
      }
      result = (result + value) % MOD;
    }
    return result;
  }

}
