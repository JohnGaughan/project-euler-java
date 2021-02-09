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
import us.coffeecode.project_euler.common.Pandigital;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=41">Problem 41</a> asks us to find the largest n-digit pandigital number
 * that is also prime.
 * </p>
 * <p>
 * This has to be a brute force algorithm: the only question is what is a reasonable upper bound on the result? Since
 * the number is pandigital, we know its digits, so any property that relies on its digits might help. For example: a
 * 9-pandigital number's digits add up to 45, which is divisible by 3. An 8-pandigital number's digits add up to 36,
 * also divisible by 3. Neither type of number can be prime. However, a 7-pandigital number's digits add up to 28, which
 * is not divisible by 3, and these can be prime. Set the upper bound to 7,654,321 and work backwards through the
 * primes.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "41")
public class Solver_0041
implements ISolver {

  private static final int LIMIT = 7_654_321;

  private final IPrimeProvider primeProvider;

  private final Pandigital pandigital;

  @Autowired
  public Solver_0041(final IPrimeProvider provider, final Pandigital pandigit) {
    primeProvider = provider;
    pandigital = pandigit;
  }

  @Override
  public long getExpectedResult() {
    return 7_652_413;
  }

  @Override
  public long getActualResult() {
    final int[] primes = primeProvider.getPrimesEqualToOrLessThan(LIMIT);
    for (int i = primes.length - 1; i >= 0; --i) {
      if (pandigital.is(primes[i])) {
        return primes[i];
      }
    }
    return -1;
  }

}
