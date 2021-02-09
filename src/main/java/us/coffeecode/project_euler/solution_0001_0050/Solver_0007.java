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
 * <a href="https://projecteuler.net/problem=7">Problem 7</a> asks us to find the 10,001st prime number.
 * </p>
 * <p>
 * Delegate to the prime provider and ask it for the given prime number.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "7")
public class Solver_0007
implements ISolver {

  private static final int INPUT = 10_001;

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0007(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return 104_743;
  }

  @Override
  public long getActualResult() {
    final int[] primes = primeProvider.getFirstNPrimes(INPUT);
    return primes[primes.length - 1];
  }

}
