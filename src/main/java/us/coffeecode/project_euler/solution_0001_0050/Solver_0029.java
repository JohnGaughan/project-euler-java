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

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=29">Problem 29</a> asks us to find the number of distinct powers
 * <code>a<sup>b</sup></code> where both a and b are between 2 and 100, inclusive.
 * </p>
 * <p>
 * The brute force method iterates over terms, calculates, and puts them in a hash set which handles uniqueness for us:
 * no need to detect duplicates. I use BigInteger here because many of the calculated values are far, far too large to
 * fit in any primitive without loss of precision. I am not aware of any useful optimizations here. The only way I know
 * of to reduce the problem is to apply mathematical techniques to figure out how many duplicates there are, and
 * subtract from the total number of combinations of base and power. However, directly calculating the answer goes
 * against the spirit of a programming puzzle.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "29")
public class Solver_0029
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 9_183;
  }

  @Override
  public long getActualResult() {
    // Size the hash table so it can fit all the numbers, considering its load factor.
    final Set<BigInteger> terms = new HashSet<>(16384);
    for (int base = 2; base <= 100; ++base) {
      for (int power = 2; power <= 100; ++power) {
        final BigInteger term = BigInteger.valueOf(base).pow(power);
        terms.add(term);
      }
    }
    return terms.size();
  }

}
