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

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=53">Problem 53</a> asks us to find the number of combinations greater than
 * one million given a set with 1 ≤ n ≤ 100 elements and selecting any number of elements from each set.
 * </p>
 * <p>
 * The key here is recognizing that this is an exercise in basic combinations. There are likely to be <i>many</i>
 * duplicate calculations, so caching in advance is helpful. Furthermore, the calculated numbers are all elements of
 * Pascal's Triangle. If we build up a triangle, which is a trivial and efficient operation, we can perform simple array
 * lookups when iterating the various inputs. This means that even with nested loops, real-world performance is
 * perfectly fine.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "53")
public class Solver_0053
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 4_075;
  }

  @Override
  public long getActualResult() {
    final int[][] pt = initPascalTriangle();
    int result = 0;
    for (int n = 1; n <= 100; ++n) {
      for (int r = 1; r <= n; ++r) {
        if (pt[n][r] > 1_000_000) {
          ++result;
        }
      }
    }
    return result;
  }

  private int[][] initPascalTriangle() {
    final int[][] triangle = new int[101][];
    // Base case: row 0 is a single 1.
    triangle[0] = new int[1];
    triangle[0][0] = 1;
    // Iterate over each other row, using the previous row's numbers.
    for (int n = 1; n < triangle.length; ++n) {
      triangle[n] = new int[n + 1];
      for (int k = 0; k < triangle[n].length; ++k) {
        // Edges are always 1.
        if ((k == 0) || (k == triangle[n].length - 1)) {
          triangle[n][k] = 1;
        }
        else {
          triangle[n][k] = triangle[n - 1][k - 1] + triangle[n - 1][k];
          // Do not store values over 1,000,001: this triangle will overflow, and we don't care about those values
          // anyway.
          triangle[n][k] = Math.min(triangle[n][k], 1_000_001);
        }
      }
    }
    return triangle;
  }

}
