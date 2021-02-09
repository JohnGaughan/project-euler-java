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

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=39">Problem 39</a> asks us to find a perimeter less than or equal to 1,000
 * such that it has the most right triangles with that perimeter and integer side lengths.
 * </p>
 * <p>
 * The brute force algorithm checks over 82,000,000 triangles, most of which are not viable. There is a clever
 * optimization, though, using the facts that <code>a<sup>2</sup> + b<sup>2</sup> = c<sup>2</sup></code>,
 * <code>a &lt; b &lt; c</code>, and <code>p = a + b + c</code>. First, however, we know that the perimeter must be even
 * from Euclid's formulas for generating pythagorean triplets. One leg is always even. The hypotenuse and other leg are
 * either both even or both odd. This removes half the search space right away. Given the equations and inequality, we
 * can set up two nested loops for iterating over perimeter and one leg. Given that one leg, we can plug numbers into
 * the equations to get the length of the other leg using only the first leg and perimeter: the hypotenuse is reduced
 * out because it can be derived from the other values anyway. Once this is done we know the length of the second leg:
 * the only question is whether it has an integer length or not, which modulus answers for us because it has division in
 * its identity.
 * </p>
 * <p>
 * These optimizations reduce the iterations required by around 1,000.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "39")
public class Solver_0039
implements ISolver {

  /** The smallest perimeter that works, a 3-4-5 triangle. */
  private static final int MINIMUM = 12;

  private static final int LIMIT = 1_000;

  @Override
  public long getExpectedResult() {
    return 840;
  }

  @Override
  public long getActualResult() {
    int mostTriangles = -1;
    int mostPerimeter = -1;
    for (int perimeter = MINIMUM; perimeter <= LIMIT; perimeter += 2) {
      int triangles = countTriangles(perimeter);
      if (triangles > mostTriangles) {
        mostPerimeter = perimeter;
        mostTriangles = triangles;
      }
    }
    return mostPerimeter;
  }

  private int countTriangles(final int perimeter) {
    int triangles = 0;
    for (int a = 1; a < perimeter / 3; ++a) {
      int b1 = perimeter * (perimeter - 2 * a);
      int b2 = (perimeter - a) << 1;
      if (b1 % b2 == 0) {
        ++triangles;
      }
    }
    return triangles;
  }

}
