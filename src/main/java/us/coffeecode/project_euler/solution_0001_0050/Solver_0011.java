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

import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=11">Problem 11</a> asks us to find the largest product of any four numbers
 * in the grid that are in a straight line in any direction.
 * </p>
 * <p>
 * This solution performs a brute force search. There are some zeros in the input, however, optimizing to reduce the
 * search space is a bit messy and the input size is small.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "11")
public class Solver_0011
implements ISolver {

  private static final int CONSECUTIVE = 4;

  @Override
  public long getExpectedResult() {
    return 70_600_674;
  }

  @Override
  public long getActualResult() {
    final int[][] grid = loadInput();
    final int side = grid.length;

    // Iterate through the grid. Try looking right, down, and diagonally down-right. Whatever is possible, calculate it.
    int max = 0;
    for (int y = 0; y < side; ++y) {
      for (int x = 0; x < side; ++x) {
        final boolean right = y + CONSECUTIVE - 1 < side;
        final boolean down = x + CONSECUTIVE - 1 < side;
        final boolean up = x - CONSECUTIVE + 1 >= 0;
        // Try right.
        if (right) {
          int candidate = 1;
          for (int i = 0; i < CONSECUTIVE; ++i) {
            candidate *= grid[y + i][x];
          }
          max = Math.max(max, candidate);
        }
        // Try down.
        if (down) {
          int candidate = 1;
          for (int i = 0; i < CONSECUTIVE; ++i) {
            candidate *= grid[y][x + i];
          }
          max = Math.max(max, candidate);
        }
        // Try down and right.
        if (down && right) {
          int candidate = 1;
          for (int i = 0; i < CONSECUTIVE; ++i) {
            candidate *= grid[y + i][x + i];
          }
          max = Math.max(max, candidate);
        }
        // Try up and right.
        if (up && right) {
          int candidate = 1;
          for (int i = 0; i < CONSECUTIVE; ++i) {
            candidate *= grid[y + i][x - i];
          }
          max = Math.max(max, candidate);
        }
      }
    }
    return max;
  }

  private static final Pattern SEPARATOR = Pattern.compile(",");

  private int[][] loadInput() {
    try {
      return Files.readAllLines(getInputPath()).stream().map(
        s -> Stream.of(SEPARATOR.split(s)).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
