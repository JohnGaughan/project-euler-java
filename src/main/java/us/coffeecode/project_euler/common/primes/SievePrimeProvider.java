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
package us.coffeecode.project_euler.common.primes;

import java.math.RoundingMode;
import java.util.Arrays;

import com.google.common.math.IntMath;

/**
 * <p>
 * Prime provider that finds primes via sieving. It caches the results across all instances, so the sieving only needs
 * to be done once. Since so many Project Euler problems require large numbers of primes, this caching actually does
 * make a noticeable difference.
 * </p>
 * <p>
 * This class is not efficient for using in multiple solutions due to repeated effort. Instead, it is only used to
 * populate a file cache which can be used between runs. The first time running unit tests this class will sieve plenty
 * of primes, which the {@link FileCachePrimeProvider} will save to a file and read as needed from then on, even across
 * multiple executions of unit tests.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
class SievePrimeProvider
implements IPrimeProvider {

  @Override
  public int[] getFirstNPrimes(final int quantityOfPrimes) {
    /*
     * Handle the case where quantity of primes is less than six, then delegate to another method if sieving is actually
     * required.
     */
    if (quantityOfPrimes < 0) {
      throw new IllegalArgumentException("Quantity of primes " + quantityOfPrimes + " must be nonnegative");
    }
    else if (quantityOfPrimes == 0) {
      return new int[0];
    }
    else if (quantityOfPrimes == 1) {
      return new int[] { 2 };
    }
    else if (quantityOfPrimes == 2) {
      return new int[] { 2, 3 };
    }
    else if (quantityOfPrimes == 3) {
      return new int[] { 2, 3, 5 };
    }
    else if (quantityOfPrimes == 4) {
      return new int[] { 2, 3, 5, 7 };
    }
    else if (quantityOfPrimes == 5) {
      return new int[] { 2, 3, 5, 7, 11 };
    }
    return getFirstNPrimesBySieving(quantityOfPrimes);
  }

  private int[] getFirstNPrimesBySieving(final int quantityOfPrimes) {
    /*
     * This uses the inequality PI(n) < n(ln n + ln ln n) for n >= 6 where n = quantityOfPrimes. For more info:
     * http://math.stackexchange.com/a/2146323/
     */
    final double n = quantityOfPrimes;
    final double ln_n = Math.log(n);
    final double ln_ln_n = Math.log(ln_n);
    final double sieveSize = n * (ln_n + ln_ln_n);
    if (sieveSize > Integer.MAX_VALUE) {
      /*
       * Since array lengths fit in int in Java we can _always_ return the first (int n) primes: however, this algorithm
       * needs to fit those primes in a larger sieve which is too big for a Java array.
       */
      throw new IllegalArgumentException("Quantity of primes " + quantityOfPrimes + " is too large");
    }

    // Fill the sieve.
    int[] primes = getPrimesEqualToOrLessThan((int) sieveSize);
    if (primes.length == quantityOfPrimes) {
      return primes;
    }
    else if (primes.length > quantityOfPrimes) {
      return Arrays.copyOf(primes, quantityOfPrimes);
    }
    // Sieve size was calculated incorrectly: however, this should never happen.
    throw new AssertionError("QuantityOfPrimes [" + quantityOfPrimes + "], d [" + sieveSize + "]");
  }

  @Override
  public int[] getPrimesEqualToOrLessThan(final int ceiling) {
    final boolean[] sieve = makeSieve(ceiling);
    doSieve(sieve);
    return collectPrimes(sieve);
  }

  /** Construct a sieve, where every value except 1 defaults to being prime to start. */
  private boolean[] makeSieve(final int size) {
    final boolean[] sieve = new boolean[size];

    // Note: this intentionally skips the first element, number 1.
    for (int i = 1; i < sieve.length; ++i) {
      sieve[i] = true;
    }

    return sieve;
  }

  /** Perform the sieve operation, marking off composites. */
  private void doSieve(final boolean[] sieve) {
    final int max = IntMath.sqrt(sieve.length, RoundingMode.DOWN);
    for (int i = 1; i < max; ++i) {
      if (sieve[i]) {
        // Add one to account for the 0-index
        final int value = i + 1;
        // Increment by 2 when realI is 2, otherwise skip the even multiples.
        final int increment = value == 2 ? value : value << 1;
        // Starting at value^2, mark multiples as composite.
        for (int j = value * value - 1; j < sieve.length; j += increment) {
          sieve[j] = false;
        }
      }
    }
  }

  /** Given a processed sieve, collect the primes into an integer array. */
  private int[] collectPrimes(final boolean[] sieve) {
    final int[] primes = new int[countPrimes(sieve)];
    int currentIndex = 0;
    for (int i = 1; i < sieve.length; ++i) {
      if (sieve[i]) {
        primes[currentIndex] = i + 1;
        ++currentIndex;
      }
    }
    return primes;
  }

  /** Count the quantity of prime numbers in the sieve. */
  private int countPrimes(final boolean[] sieve) {
    int primesFound = 0;
    for (final boolean primeCandidate : sieve) {
      if (primeCandidate) {
        ++primesFound;
      }
    }
    return primesFound;
  }

}
