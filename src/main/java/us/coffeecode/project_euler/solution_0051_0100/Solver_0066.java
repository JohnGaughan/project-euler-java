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

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=66">Problem 66</a> asks us to find values that satisfy
 * <code>x^2 - Dy^2 = 1</code>. For each D &lt;= 1000, find the smallest x that is part of an integer solution. What is
 * the largest such x?
 * </p>
 * <p>
 * The problem title gives a clue. Turns out this is a specialized Diophantine equation:
 * <a href="https://en.wikipedia.org/wiki/Pell%27s_equation">Pell's equation</a>. There seem to be two primary methods
 * for solving this, and both focus on square roots of D. Either use continued fractions to converge on a solution for X
 * after rearranging the equation, or use the <a href="https://en.wikipedia.org/wiki/Chakravala_method">Chakravala
 * method</a> to figure out the cycle length of the decimal expansion of the square root. Long lengths mean larger X.
 * Since we do not need to know X, just the D that correlates with the largest X, we can bypass calculating the actual
 * fractions.
 * </p>
 * <p>
 * Honestly, a lot of this math is above my head and I relied on a rabbit hole of reading starting with Wikipedia to
 * figure this out. There are algorithms listed there and on a few other sites, which I converted to Java. One
 * optimization is that we only need to consider values of D that are prime. I am not sure why, but the math blogs
 * agree. Next, some of these values are excessively large. BigInteger math is required all around. The largest value of
 * X does not even fit in a long.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan &lt;john@coffeecode.us&gt;
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "66")
public class Solver_0066
implements ISolver {

  @Autowired
  private IPrimeProvider primes;

  /** {@inheritDoc} */
  @Override
  public long getExpectedResult() {
    return 661;
  }

  /** {@inheritDoc} */
  @Override
  public long getActualResult() {
    BigInteger max = BigInteger.ZERO;
    long answer = -1;
    for (long d : primes.getPrimesEqualToOrLessThan(1_000)) {
      final BigInteger x = solve(BigInteger.valueOf(d));
      if (x.compareTo(max) > 0) {
        max = x;
        answer = d;
      }
    }
    return answer;
  }

  private BigInteger solve(final BigInteger d) {
    final BigInteger root = d.sqrt();
    BigInteger a = BigInteger.ONE;
    BigInteger b = BigInteger.ONE;
    BigInteger xPrime = BigInteger.ONE;
    BigInteger y = BigInteger.ZERO;
    BigInteger x = BigInteger.ZERO;
    while (!b.equals(BigInteger.ONE) || y.equals(BigInteger.ZERO)) {
      a = b.multiply(a.divide(b).add(BigInteger.ONE)).subtract(a);
      a = a.subtract(a.subtract(root).divide(b).multiply(b));

      x = a.multiply(xPrime).add(d.multiply(y)).divide(b.abs());
      y = a.multiply(y).add(xPrime).divide(b.abs());
      b = a.multiply(a).subtract(d).divide(b);
      xPrime = x;
    }
    return x;
  }

}
