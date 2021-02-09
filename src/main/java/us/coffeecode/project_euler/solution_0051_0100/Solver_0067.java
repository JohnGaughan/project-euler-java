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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=67">Problem 67</a> asks us to
 * </p>
 * <p>
 * This solver constructs a double array of integers, where each sub-array is one larger than the one that proceeds it.
 * Only the maximum value from the top to the bottom is required: not the path taken. This algorithm destructively
 * replaces the values in each row. At each step, take the larger of the two children (left is down one row, right is
 * down and right one) and add the value of the current node. That is the solution for that node. By the time the
 * algorithm iterates to the top row, with its single node, the solution is simply at index (0, 0). This avoid many
 * redundant calculations in the naive approach (depth probing), reducing the running time to O(n^2) where n is the
 * number of rows (also the width of the bottom row). This is similar to
 * {@link net.johngaughan.project_euler.solution_0001_0050.Solver_0018} and uses the same algorithm, except this one
 * loads input from a file.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "67")
public class Solver_0067
implements ISolver {

  private static final Pattern SEPARATOR = Pattern.compile(" ");

  private static final Path INPUT = Path.of("input-problem-0067.txt");

  @Override
  public long getExpectedResult() {
    return 7_273;
  }

  @Override
  public long getActualResult() {
    final int[][] triangle = getInput();
    for (int row = triangle.length - 2; row >= 0; --row) {
      for (int col = 0; col < triangle[row].length; ++col) {
        triangle[row][col] += Math.max(triangle[row + 1][col], triangle[row + 1][col + 1]);
      }
    }
    return triangle[0][0];
  }

  private int[][] getInput() {
    try {
      return Files.readAllLines(INPUT).stream().map(
        s -> Arrays.stream(SEPARATOR.split(s)).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
