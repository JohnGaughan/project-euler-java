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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=26">Problem 26</a> asks us to find the largest number less than 1,000 where
 * 1/n has the longest cycle in its decimal expansion.
 * </p>
 * <p>
 * First, we know that n has to be prime because composites have shorter cycles than primes. That removes part of the
 * search space. Next, when finding the cycle, we don't even care about what the result is: we just care about the cycle
 * length. If we picture long division with one under the division bar, we keep bringing down zeros because it is 1.000
 * repeating. This means that when we do a partial division we multiply its remainder by ten. If we ever encounter that
 * remainder again, we know it will give the exact same result and is part of a cycle. Then we just need to find the
 * first and last occurrences of that remainder to find the cycle length.
 * </p>
 * <p>
 * The algorithm simply iterates the primes and uses that cycle algorithm, checking against the current longest cycle
 * until exhausting all primes. This ends with the answer to the problem.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "26")
public class Solver_0026
implements ISolver {

  private static final int LIMIT = 1000;

  private final IPrimeProvider primes;

  @Autowired
  public Solver_0026(final IPrimeProvider p) {
    primes = p;
  }

  @Override
  public long getExpectedResult() {
    return 983;
  }

  @Override
  public long getActualResult() {
    int numWithLongestCycle = -1;
    int longestCycle = -1;
    for (int i : primes.getPrimesEqualToOrLessThan(LIMIT)) {
      final int cycleLength = getCycle(i);
      if (cycleLength > longestCycle) {
        numWithLongestCycle = i;
        longestCycle = cycleLength;
      }
    }
    return numWithLongestCycle;
  }

  private int getCycle(final int divisor) {
    final List<Integer> divisions = new ArrayList<>(990);

    int dividend = 1;

    Integer last = null;
    while (true) {
      dividend = 10 * (dividend % divisor);
      last = Integer.valueOf(dividend);

      if (divisions.contains(last)) {
        break;
      }
      divisions.add(last);
    }

    final int first = divisions.indexOf(last);
    return divisions.size() - first;
  }

}
