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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=69">Problem 69</a> asks us to find the maximum value of n/φ(n), where n ≤
 * 1,000,000.
 * </p>
 * <p>
 * Calculating prime factorizations is slow, even with memoization. Several implementations produced correct results,
 * but took many minutes to run. The key insight here is running an algorithm that may not methodically find the correct
 * answer, but uses assumptions to prune the search space. The key here is we need to maximize the ratio. N needs to be
 * high, but φ(n) needs to be small. When N is prime, the ratio is small, because φ(n) is equal to N-1. When N is highly
 * composite, meaning it has a lot of factors, φ(n) is small. A small denominator makes the ratio large.
 * </p>
 * <p>
 * We need an N with a lot of factors. Since we only care about unique prime factors, the naive approach is to multiply
 * all of the prime numbers, small to large, until we get the largest number under the ceiling. It turns out that this
 * value of N is the answer. It turns out that the naive approach produces the correct answer in this case.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "69")
public class Solver_0069
implements ISolver {

  @Autowired
  private IPrimeProvider primeProvider;

  @Override
  public long getExpectedResult() {
    return 510_510;
  }

  @Override
  public long getActualResult() {
    int answer1 = 1;
    int answer2 = 1;
    final int[] primes = primeProvider.getPrimesEqualToOrLessThan(17);
    for (int i = 0; i < primes.length; ++i) {
      answer2 = answer1 * primes[i];
      if (answer2 > 1_000_000) {
        break;
      }
      answer1 = answer2;
    }
    return answer1;
  }

}
