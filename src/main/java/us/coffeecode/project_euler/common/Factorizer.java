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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Function that gets the factors of a number, and caches the results.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component
public class Factorizer {

  private final Map<Integer, int[]> factorCache = new HashMap<>();

  /**
   * Get all regular factors of a given number. These factors include one, but not the number itself.
   *
   * @param number the number for which to get the factorization.
   * @return the factors, in ascending order. Includes one but not the number itself.
   */
  public int[] factor(final int value) {
    final Integer key = Integer.valueOf(value);
    if (!factorCache.containsKey(key)) {
      final int ceiling = value >> 1;
      int found = 0;
      int[] factors = new int[ceiling];
      for (int candidate = 1; candidate <= ceiling; ++candidate) {
        if (value % candidate == 0) {
          factors[found] = candidate;
          ++found;
        }
      }
      if (found < factors.length) {
        factors = Arrays.copyOf(factors, found);
      }
      factorCache.put(key, factors);
    }
    return factorCache.get(key);
  }

}
