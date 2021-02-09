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

import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.math.LongMath;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=3">Problem 3</a> asks us to find the largest prime factor of a number.
 * </p>
 * <p>
 * Given that this project caches prime numbers, the simplest approach is to get all prime numbers up to the number's
 * square root. Then, working backwards from the largest numbers, find the first one that evenly divides the number.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "3")
public class Solver_0003
implements ISolver {

  private static final long INPUT = 600_851_475_143L;

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0003(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return 6_857;
  }

  @Override
  public long getActualResult() {
    // Only need to go up to the square root of the given value. While the
    // value does not fit into an int, its square root does.
    final int ceiling = (int) LongMath.sqrt(INPUT, RoundingMode.DOWN);

    /*
     * Since we need the largest prime factor, start with the largest prime number we have and work backwards until one
     * of them divides the value.
     */
    final int[] primes = primeProvider.getPrimesEqualToOrLessThan(ceiling);
    for (int i = primes.length - 1; i >= 0; --i) {
      if (INPUT % primes[i] == 0) {
        return primes[i];
      }
    }
    return Long.MIN_VALUE;
  }

}
