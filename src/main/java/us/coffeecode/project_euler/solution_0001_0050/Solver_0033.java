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

import org.springframework.stereotype.Component;

import com.google.common.math.IntMath;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=33">Problem 33</a> asks us to find four fractions between zero and one with
 * two digits in both the numerator and denominator, multiply them, reduce, and give the denominator. The four fractions
 * can be reduced by removing the ones digit from top and bottom, but those digits are not zero.
 * </p>
 * <p>
 * This algorithm iterates over digits in the fraction, finds the cross product, and checks for equality. If so, we
 * multiply the components corresponding to the reduced fraction. At the end we apply some basic algebra to reduce the
 * denominator, if possible.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "33")
public class Solver_0033
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 100;
  }

  @Override
  public long getActualResult() {
    int numerator = 1;
    int denominator = 1;
    for (int a = 1; a < 9; ++a) {
      for (int b = 1; b < 10; ++b) {
        for (int c = a + 1; c < 10; ++c) {
          if ((10 * a * b) + (a * c) == (10 * a * c) + (b * c)) {
            numerator *= a;
            denominator *= c;
          }
        }
      }
    }
    return denominator / IntMath.gcd(numerator, denominator);
  }

}
