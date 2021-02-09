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
 * <a href="https://projecteuler.net/problem=37">Problem 37</a> asks us to find all primes that are also prime if we
 * remove digits from the left and the right.
 * </p>
 * <p>
 * Brute force our way through the primes. The problem states there are eleven, so if we count them as we go along, we
 * know the exit condition. The only micro-optimization here is checking for right-truncation first, since there is less
 * work to do. Short circuiting saves a tiny amount of time.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "37")
public class Solver_0037
implements ISolver {

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0037(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return 748_317;
  }

  @Override
  public long getActualResult() {
    final int[] primes = primeProvider.getFirstNPrimes(60_000);
    int result = 0;
    for (int i = 4, found = 0; i < primes.length && found < 11; ++i) {
      if (isTruncatableRight(primes[i], primes) && isTruncatableLeft(primes[i], primes)) {
        ++found;
        result += primes[i];
      }
    }
    return result;
  }

  private boolean isTruncatableRight(final int value, final int[] primes) {
    int v = value / 10;
    while (v > 0) {
      if (Arrays.binarySearch(primes, v) < 0) {
        return false;
      }
      v /= 10;
    }
    return true;
  }

  private boolean isTruncatableLeft(final int value, final int[] primes) {
    int v = truncateLeft(value);
    while (v > 0) {
      if (Arrays.binarySearch(primes, v) < 0) {
        return false;
      }
      v = truncateLeft(v);
    }
    return true;
  }

  private int truncateLeft(final int value) {
    int numDigits = 0;
    int v = value;
    while (v > 0) {
      ++numDigits;
      v /= 10;
    }
    int[] newDigits = new int[numDigits - 1];
    v = value;
    for (int i = newDigits.length - 1; i >= 0; --i) {
      newDigits[i] = v % 10;
      v /= 10;
    }
    v = 0;
    for (int digit : newDigits) {
      v *= 10;
      v += digit;
    }
    return v;
  }

}
