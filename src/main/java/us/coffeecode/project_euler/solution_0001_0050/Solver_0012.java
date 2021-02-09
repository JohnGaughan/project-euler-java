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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=12">Problem 12</a> asks us to find the first triangle number that has over
 * 500 divisors.
 * </p>
 * <p>
 * T(n) = n(n+1)/2 n and n+1 must be coprime: they have no common divisors. However, the two factors combine to give
 * divisors for the full expression. This means that it is not necessary to count divisors for the full number, which
 * takes a lot more time. At each step, count divisors for either (n+1) if n is even, or (n+1)/2 if n is odd. Alternate
 * between them, since the factors are shared between consecutive values of T(n). During factorization, get the prime
 * factorization. Start with 1, since that is not prime but is always a factor. For each prime number, count the number
 * of times it divides the number. Keep a running multiple of of divisors, and multiply this by the primes exponent:
 * essentially, count the number of combinations of all of the primes, which gives the number of divisors. If a prime is
 * greater than the square root of the number, stop processing because the remaining primes will not "do" anything.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "12")
public class Solver_0012
implements ISolver {

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0012(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return 76_576_500;
  }

  @Override
  public long getActualResult() {
    int i = 2;
    final int[] primes = primeProvider.getFirstNPrimes(200);
    boolean even = true;
    for (int dn1 = 2, dn = 2, cnt = 0; cnt <= 500; ++i, even = !even, cnt = dn * dn1) {
      if (even) {
        dn = getTotalFactors(primes, i + 1);
      }
      else {
        dn1 = getTotalFactors(primes, (i + 1) / 2);
      }
    }
    return i * (i - 1) / 2;
  }

  private int getTotalFactors(final int[] primes, final int number) {
    int divisors = 1;
    int remain = number;

    for (final int prime : primes) {

      // In case there is a remainder this is a prime factor as well
      // The exponent of that factor is 1
      if (prime * prime > number) {
        return divisors * 2;
      }

      int exponent = 1;
      while (remain % prime == 0) {
        ++exponent;
        remain /= prime;
      }
      divisors *= exponent;

      // If there is no remainder, return the count
      if (remain == 1) {
        return divisors;
      }
    }
    return divisors;
  }

}
