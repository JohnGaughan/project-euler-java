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
import us.coffeecode.project_euler.common.PermutationCheck;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=49">Problem 49</a> asks us to find a sequence of four-digit numbers that
 * are all prime and equidistant. The problem provides one such sequence, and we need to find the only other one.
 * </p>
 * <p>
 * Brute force our way through all the primes, starting after the first prime provided by the problem.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "49")
public class Solver_0049
implements ISolver {

  @Autowired
  private IPrimeProvider primeProvider;

  @Autowired
  private PermutationCheck permutations;

  @Override
  public long getExpectedResult() {
    return 296_962_999_629L;
  }

  @Override
  public long getActualResult() {
    final int[] primes = getFourDigitPrimes();
    for (int i = 0; i < primes.length; ++i) {
      for (int j = i + 1; j < primes.length; ++j) {
        final int k = (primes[j] << 1) - primes[i];
        if ((0 <= Arrays.binarySearch(primes, k)) && permutations.test(primes[i], primes[j])
          && permutations.test(primes[i], k)) {
          return k + 10_000L * primes[j] + 100_000_000L * primes[i];
        }
      }
    }
    return -1;
  }

  private int[] getFourDigitPrimes() {
    final int[] primes = primeProvider.getPrimesEqualToOrLessThan(9_999);
    // Skip the first sequence identified in the problem.
    final int first = Arrays.binarySearch(primes, 1_489);
    return Arrays.copyOfRange(primes, first, primes.length);
  }

}
