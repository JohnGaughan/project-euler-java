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

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.PermutationCheck;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=62">Problem 62</a> asks us to find the smallest number that is a perfect
 * cube, and whose digits can be rearranged to have five perfect cubes.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "62")
public class Solver_0062
implements ISolver {

  @Autowired
  private PermutationCheck permutation;

  @Override
  public long getExpectedResult() {
    return 127_035_954_683L;
  }

  @Override
  public long getActualResult() {
    final long[] cubes = LongStream.rangeClosed(5_000, 9_000).map(n -> (n * n * n)).toArray();
    for (int i = 0; i < cubes.length - 5; ++i) {
      // Get permutations and count how many exist in the cubes. If five, stop, we have our smallest number.
      final int ii = i;
      final int permutations =
        1 + (int) IntStream.range(i + 1, cubes.length).filter(j -> permutation.test(cubes[ii], cubes[j])).count();
      if (permutations == 5) {
        return cubes[i];
      }
    }
    return -1;
  }

}
