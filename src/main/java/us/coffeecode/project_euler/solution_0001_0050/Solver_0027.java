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
 * <a href="https://projecteuler.net/problem=27">Problem 27</a> asks us to find a quadratic meeting certain criteria for
 * its coefficients that produces the longest series of prime numbers, then provide the product of the coefficients.
 * </p>
 * <p>
 * Brute force provides the answer and in a reasonable amount of time. However, there are optimizations we can apply.
 * </p>
 * <p>
 * Since the quadratic has to provide primes even when n is zero, that means b must be prime. This also means b must be
 * positive, even though the problem states it can go down to -1,000. By restricting B to primes, this reduces the
 * search space by over a factor of ten.
 * </p>
 * <p>
 * The answer to the problem is odd, meaning that neither a nor b can be even. Since b is already going to be odd
 * because two does not provide the correct answer, a must be odd. So skip by two when iterating a.
 * </p>
 * <p>
 * If n is zero, the quadratic must be positive since it reduces to b. If n is one, then it reduces to
 * <code>q = 1 + a + b</code> which must be positive to produce anything useful. We can build an inequality from that:
 * <code>a + b + 1 > 0</code>. The minimum integer that satisfies the right-hand side is one. Converting to an equation:
 * <code>a + b + 1 = 1</code>. Reducing this, we get <code>a = -b</code>. This is the starting point for each loop over
 * a. This will produce incorrect results when b = 2, but again, we know this is not the right answer anyway, so it does
 * not hurt anything.
 * </p>
 * <p>
 * All together, these quick optimizations reduce the iteration count from 3,999,999 to 122,147.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "27")
public class Solver_0027
implements ISolver {

  private static final int LIMIT = 1_000;

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0027(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return -59_231;
  }

  @Override
  public long getActualResult() {
    final int[] primes = primeProvider.getFirstNPrimes(1_299_709);
    final int[] candidateBs = primeProvider.getPrimesEqualToOrLessThan(LIMIT);
    int bestProduct = 0;
    int longestPrimeSequence = 0;
    for (int b : candidateBs) {
      for (int a = -b; a < LIMIT; a += 2) {
        final int primeSequence = getPrimeSequenceLength(a, b, primes);
        if (primeSequence > longestPrimeSequence) {
          bestProduct = a * b;
          longestPrimeSequence = primeSequence;
        }
      }
    }
    return bestProduct;
  }

  private int getPrimeSequenceLength(final int a, final int b, final int[] primes) {
    int n = 0;
    while (0 <= Arrays.binarySearch(primes, n * n + a * n + b)) {
      ++n;
    }
    return n;
  }

}
