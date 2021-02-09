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
package us.coffeecode.project_euler.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Calculates the prime factorization of a number and caches the results.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component
public class PrimeFactorizer {

  private final Map<Integer, List<int[]>> primeCache = new HashMap<>();

  /**
   * Get the prime factors of the given number.
   *
   * @param primes prime number cache.
   * @param number the number for which to get the prime factorization.
   * @return prime factors, in ascending order. Each list element is a two-element array: the first value is the factor,
   * the second is the number of times it appears.
   */
  public List<int[]> factor(final int[] primes, final int number) {
    final Integer key = Integer.valueOf(number);
    if (!primeCache.containsKey(key)) {
      final List<int[]> factors = new LinkedList<>();
      int value = number;
      // Iterate over the primes, divide the number as we go until there are no more factors left (it ends up at 1).
      for (int i = 0; i < primes.length && value > 1; ++i) {
        int appearances = 0;
        while (value % primes[i] == 0) {
          value /= primes[i];
          ++appearances;
        }
        if (appearances > 0) {
          factors.add(new int[] { primes[i], appearances });
        }
      }
      primeCache.put(key, factors);
    }
    return primeCache.get(key);
  }

}
