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
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.math.IntMath;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=46">Problem 46</a> asks us to examine Goldbach's other conjecture and find
 * the smallest odd composite that cannot be written as the sum of a prime and twice a square.
 * </p>
 * <p>
 * Iterate through the odd numbers. If the number is not prime and cannot pass the conjecture test, then it is the
 * smallest number that meets the problem's criteria.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "46")
public class Solver_0046
implements ISolver {

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0046(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return 5_777;
  }

  @Override
  public long getActualResult() {
    final int[] primes = primeProvider.getFirstNPrimes(1_000_000);
    for (int i = 35; i < Integer.MAX_VALUE; i += 2) {
      if ((Arrays.binarySearch(primes, i) < 0) && !test(i, primes)) {
        return i;
      }
    }
    return -1;
  }

  /** Test a number to see if it can be expressed as the sum of a prime and twice a square. */
  private boolean test(final int n, final int[] primes) {
    boolean result = false;
    for (final int p : primes) {
      // Twice a square must be even. Since n is odd, the difference (prime) must be odd. This helps with integer math.
      if (p == 2 || p > n - 1) {
        continue;
      }
      final int diff = n - p;
      final int square = diff >> 1;
      final int sqrt = IntMath.sqrt(square, RoundingMode.DOWN);
      if (sqrt * sqrt == square) {
        result = true;
        break;
      }
    }
    return result;
  }

}
