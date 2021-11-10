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
 * <a href="https://projecteuler.net/problem=51">Problem 51</a> asks us to find the smallest prime number where if we
 * change some of its digits to be the same, iterating 0-9, we get eight prime numbers total.
 * </p>
 * <p>
 * This algorithm checks several patterns of replacement, one at a time, and looks for prime families based on replacing
 * digits. Once it finds a family with the required size, it returns its smallest member.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "51")
public class Solver_0051
implements ISolver {

  // @formatter:off
  /** True indicates the digit in that position will be replaced. */
  private static final boolean[][] table5 = new boolean[][] {
    {false, true, true, true, false},
    {true, false, true, true, false},
    {true, true, false, true, false},
    {true, true, true, false, false},
  };
  /** True indicates the digit in that position will be replaced. */
  private static final boolean[][] table6 = new boolean[][] {
    {false, false, true, true, true, false},
    {false, true, false, true, true, false},
    {false, true, true, false, true, false},
    {false, true, true, true, false, false},
    {true, false, false, true, true, false},
    {true, false, true, false, true, false},
    {true, false, true, true, false, false},
    {true, true, false, false, true, false},
    {true, true, false, true, false, false},
    {true, true, true, false, false, false}
  };
  // @formatter:on

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0051(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return 121_313;
  }

  @Override
  public long getActualResult() {
    final int[] primes = primeProvider.getFirstNPrimes(1_000_000);
    for (int digits = 11; digits < 1_000; digits += 2) {
      if (digits % 5 == 0) {
        continue;
      }
      final boolean[][] patterns = (digits < 100) ? table5 : table6;
      for (final boolean[] pattern : patterns) {
        if (getFamilySize(primes, pattern, digits) == 8) {
          return getFirstPrime(primes, pattern, digits);
        }
      }
    }
    return 0;
  }

  /** Get the size of the prime number family. */
  private int getFamilySize(final int[] primes, final boolean[] pattern, final int digits) {
    int familySize = 0;
    int notEligible = 0;
    for (int i = 0; (i < 10) && (notEligible < 3); ++i) {
      // Do not replace leading zeros.
      if ((i == 0) && pattern[0]) {
        continue;
      }
      final int num = makeNumber(pattern, digits, i);
      if (0 <= Arrays.binarySearch(primes, num)) {
        ++familySize;
      }
      else {
        ++notEligible;
      }
    }
    return familySize;
  }

  /** Get the first prime number matching the given pattern. */
  private int getFirstPrime(final int[] primes, final boolean[] pattern, final int digits) {
    for (int i = 0; i < 3; ++i) {
      final int num = makeNumber(pattern, digits, i);
      if (0 <= Arrays.binarySearch(primes, num)) {
        return num;
      }
    }
    return 0;
  }

  /** Make a number from the given replacement pattern, digits, and replacement digit. */
  protected int makeNumber(final boolean[] pattern, final int digits, final int replacementDigit) {
    int v = digits;
    int number = 0;
    int multiplier = 1;
    for (int i = pattern.length - 1; i >= 0; --i, multiplier *= 10) {
      if (pattern[i]) {
        number += replacementDigit * multiplier;
      }
      else {
        number += (v % 10) * multiplier;
        v /= 10;
      }
    }
    return number;
  }

}
