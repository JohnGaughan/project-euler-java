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

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=50">Problem 50</a> asks us to find the prime under 1,000,000 that can be
 * written as the sum of the most prime numbers.
 * </p>
 * <p>
 * To avoid a ridiculously inefficient algorithm, employ some dynamic programming by precalculating the sums of primes.
 * Then we can look up chain lengths and compare to the primes to see which sums actually correlate to prime numbers.
 * Keep track of the current prime and its chain length so we can replace it as needed.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "50")
public class Solver_0050
implements ISolver {

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0050(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return 997_651;
  }

  @Override
  public long getActualResult() {
    final int[] primes = primeProvider.getPrimesEqualToOrLessThan(1_000_000);
    final long[] primeSums = getSums(primes);
    // Need to find the greatest prime value under 1,000,000 calculated by one of the following methods:
    // 1. Is a value in the list. Consecutive primes are the size of the list.
    // 2. Is a value computed by a difference of two sums in the list. Consecutive primes are the difference of the
    // indices.
    long result = 0;
    int consecutive = 0;
    for (int i = 0; i < primeSums.length; ++i) {
      for (int j = i; j < primeSums.length; ++j) {
        final long si = primeSums[i];
        final long sj = primeSums[j];
        if (sj - si > 999_983) {
          break;
        }
        if (j - i > consecutive && 0 <= Arrays.binarySearch(primes, (int) (sj - si))) {
          result = sj - si;
          consecutive = j - i;
        }
      }
    }
    return result;
  }

  /**
   * Given primes, return a map of those primes mapped to the cumulative sum of all primes in the map equal to or lower
   * than that prime value. This is essentially dynamic programming for calculating sums of consecutive primes.
   */
  private long[] getSums(final int[] primes) {
    final long[] sums = new long[primes.length];
    sums[0] = primes[0];
    for (int i = 1; i < primes.length; ++i) {
      sums[i] = sums[i - 1] + primes[i];
    }
    return sums;
  }

}
