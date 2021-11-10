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

import java.util.Arrays;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.Pentagonal;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=44">Problem 44</a> asks us to find two pentagonal numbers where their sum
 * and difference are both pentagonal, and the difference is smaller than the different for any other such pair.
 * </p>
 * <p>
 * The problem does not give any upper bound, so just find the first one which apparently is the correct one.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "44")
public class Solver_0044
implements ISolver {

  @Autowired
  private Pentagonal pentagonal;

  @Override
  public long getExpectedResult() {
    return 5_482_660;
  }

  @Override
  public long getActualResult() {
    final long[] pentagonals = LongStream.rangeClosed(1, 2_400).map(pentagonal).toArray();
    for (int j = 0; j < pentagonals.length; ++j) {
      for (int k = j + 1; k < pentagonals.length; ++k) {
        if (Arrays.binarySearch(pentagonals, pentagonals[k] + pentagonals[j]) >= 0) {
          final long difference = pentagonals[k] - pentagonals[j];
          if (Arrays.binarySearch(pentagonals, difference) >= 0) {
            return difference;
          }
        }
      }
    }
    return -1;
  }

}
