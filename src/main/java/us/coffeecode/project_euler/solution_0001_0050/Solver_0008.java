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

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=8">Problem 8</a> asks us to search through a long series of digits and find
 * the thirteen adjacent digits with the greatest product.
 * </p>
 * <p>
 * The input is short enough that a brute force approach is feasible, and any advanced optimization likely has more
 * overhead than it saves. However, one optimization that does cut running time by around 2/3 is to detect zeros and
 * skip ahead when there is an attempt to multiply by them.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "8")
public class Solver_0008
implements ISolver {

  private static final int LENGTH = 13;

  @Override
  public long getExpectedResult() {
    return 23_514_624_000L;
  }

  @Override
  public long getActualResult() {
    final long[] input = getInput();
    long max = 0;
    for (int i = 0; i < input.length - LENGTH + 1; ++i) {
      long value = 1;
      for (int j = i; j < i + LENGTH; ++j) {
        if (input[j] == 0) {
          i = j + 1;
          break;
        }
        value *= input[j];
      }
      max = Math.max(max, value);
    }
    return max;
  }

  private long[] getInput() {
    try {
      return Files.readAllLines(getInputPath()).stream().mapToLong(Long::parseLong).toArray();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
