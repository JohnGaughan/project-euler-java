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

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=58">Problem 58</a> asks us to calculate the ratio of primes along corners
 * of a square spiral of numbers.
 * </p>
 * <p>
 * Brute force is the only way. The algorithm actually is fairly quick, with the bulk of the time spent on populating
 * the array of primes.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "58")
public class Solver_0058
implements ISolver {

  private final int[] primes;

  @Autowired
  public Solver_0058(final IPrimeProvider provider) {
    primes = provider.getFirstNPrimes(36_000_000);
  }

  @Override
  public long getExpectedResult() {
    return 26_241;
  }

  @Override
  public long getActualResult() {
    int numPrimes = 0;
    int sideLength = 1;
    final int[] corners = new int[4];
    corners[3] = 1;
    do {
      sideLength += 2;
      final int spacing = sideLength - 1;
      corners[0] = corners[3] + spacing;
      corners[1] = corners[0] + spacing;
      corners[2] = corners[1] + spacing;
      corners[3] = corners[2] + spacing;
      if (0 <= Arrays.binarySearch(primes, corners[0])) {
        ++numPrimes;
      }
      if (0 <= Arrays.binarySearch(primes, corners[1])) {
        ++numPrimes;
      }
      if (0 <= Arrays.binarySearch(primes, corners[2])) {
        ++numPrimes;
      }
    } while ((numPrimes * 10) >= ((sideLength << 1) - 1));
    return sideLength;
  }

}
