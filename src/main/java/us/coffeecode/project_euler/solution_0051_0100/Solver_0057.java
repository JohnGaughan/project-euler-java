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
 * <a href="https://projecteuler.net/problem=57">Problem 57</a> asks us to find fractional expansions of a series that
 * converges on <code>2<sup>1/2</sup></code>. Then see how many of them have a numerator with more digits than the
 * denominator.
 * </p>
 * <p>
 * The <a href="https://en.wikipedia.org/wiki/Pell_number">Wikipedia article on Pell numbers</a> shows how to calculate
 * the terms of the sequence. The algorithm simply implements that formula, and produces the answer.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "57")
public class Solver_0057
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 153;
  }

  @Override
  public long getActualResult() {
    // Term 0
    BigInteger n = BigInteger.ONE;
    BigInteger d = BigInteger.ONE;
    int result = 0;
    for (int i = 0; i < 1000; ++i) {
      BigInteger oldN = n;
      BigInteger oldD = d;
      n = oldD.multiply(BigInteger.TWO).add(oldN);
      d = oldD.add(oldN);
      if (n.toString().length() > d.toString().length()) {
        ++result;
      }
    }
    return result;
  }

}
