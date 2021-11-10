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
import us.coffeecode.project_euler.common.PrimeFactorizer;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=47">Problem 47</a> asks us to find the first four consecutive integers that
 * have four distinct prime factors each and return the first of those numbers.
 * </p>
 * <p>
 * Given the distribution of primes, this solution searches through quite a few numbers that cannot possibly be numbers.
 * However, it performs plenty quick and trying to skip over primes adds a bit of complexity that is not worth the
 * headache.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "47")
public class Solver_0047
implements ISolver {

  @Autowired
  private IPrimeProvider primeProvider;

  @Autowired
  private PrimeFactorizer primeFactorizer;

  @Override
  public long getExpectedResult() {
    return 134_043;
  }

  @Override
  public long getActualResult() {
    final int[] primes = primeProvider.getFirstNPrimes(128);
    int result = -1;
    for (int i = 210; i < Integer.MAX_VALUE && result < 0; ++i) {
      if (primeFactorizer.apply(primes, i).size() >= 4) {
        if (primeFactorizer.apply(primes, i + 1).size() >= 4) {
          if (primeFactorizer.apply(primes, i + 2).size() >= 4) {
            if (primeFactorizer.apply(primes, i + 3).size() >= 4) {
              result = i;
            }
            else {
              i += 3;
            }
          }
          else {
            i += 2;
          }
        }
        else {
          ++i;
        }
      }
    }
    return result;
  }

}
