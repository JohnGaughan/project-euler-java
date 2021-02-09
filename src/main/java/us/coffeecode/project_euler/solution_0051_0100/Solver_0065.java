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

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=65">Problem 65</a> asks us to
 * </p>
 * <p>
 * The problem gives us the sequence of convergents to plug into the continued fraction. Calculate this out to 100 terms
 * similar to {@link Solver_0057}, then add up the digits in the numerator.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "65")
public class Solver_0065
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 272;
  }

  @Override
  public long getActualResult() {
    int upperBound = 100;
    BigInteger d = BigInteger.ONE;
    BigInteger n = BigInteger.TWO;
    for (int i = 2; i <= upperBound; ++i) {
      BigInteger temp = d;
      BigInteger c = BigInteger.valueOf(i % 3 == 0 ? 2 * (i / 3) : 1);
      d = n;
      n = c.multiply(d).add(temp);
    }
    return n.toString().codePoints().map(ch -> ch - '0').sum();
  }

}
