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
 * <a href="https://projecteuler.net/problem=18">Problem 18</a> asks us to traverse a triangle and find the maximum sum
 * from top to bottom.
 * </p>
 * <p>
 * As the problem states, this is similar to problem 67
 * ({@link net.johngaughan.project_euler.solution_0051_0100.Solver_0067}). However, the input is small. Regardless, this
 * code uses the same algorithm for solving the problem so read the documentation for the other problem that I linked.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "18")
public class Solver_0018
implements ISolver {

  // @formatter:off
  private final int[][] TRIANGLE = new int[][] {
    new int[] {75},
    new int[] {95, 64},
    new int[] {17, 47, 82},
    new int[] {18, 35, 87, 10},
    new int[] {20,  4, 82, 47, 65},
    new int[] {19,  1, 23, 75,  3, 34},
    new int[] {88,  2, 77, 73,  7, 63, 67},
    new int[] {99, 65,  4, 28,  6, 16, 70, 92},
    new int[] {41, 41, 26, 56, 83, 40, 80, 70, 33},
    new int[] {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
    new int[] {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
    new int[] {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
    new int[] {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
    new int[] {63, 66,  4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
    new int[] { 4, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23}
  };
  // @formatter:on

  @Override
  public long getExpectedResult() {
    return 1_074;
  }

  @Override
  public long getActualResult() {
    for (int line = TRIANGLE.length - 2; line >= 0; --line) {
      for (int col = 0; col < TRIANGLE[line].length; ++col) {
        TRIANGLE[line][col] += Math.max(TRIANGLE[line + 1][col], TRIANGLE[line + 1][col + 1]);
      }
    }
    return TRIANGLE[0][0];
  }

}
