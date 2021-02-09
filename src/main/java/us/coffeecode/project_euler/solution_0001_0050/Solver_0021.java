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

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.Factorizer;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=21">Problem 21</a> asks us to find all pairs of numbers under 10,000 whose
 * divisors have the same sum. Then sum all of the numbers (not their divisors).
 * </p>
 * <p>
 * The key to making this simple is that applying the divisor sum transform twice will give the original number if it is
 * amicable.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "21")
public class Solver_0021
implements ISolver {

  private static final int MAX = 10_000;

  private final Factorizer factorizer;

  @Autowired
  public Solver_0021(final Factorizer factors) {
    factorizer = factors;
  }

  @Override
  public long getExpectedResult() {
    return 31_626;
  }

  @Override
  public long getActualResult() {
    return IntStream.range(1, MAX).filter(this::isAmicable).sum();
  }

  private boolean isAmicable(final int number) {
    final int sumDivisors = IntStream.of(factorizer.factor(number)).sum();
    if (sumDivisors != number) {
      final int sumDivisorsOfSumDivisors = IntStream.of(factorizer.factor(sumDivisors)).sum();
      // Does applying the transform twice give the original number?
      return number == sumDivisorsOfSumDivisors;
    }
    return false;
  }

}
