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

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=61">Problem 61</a> asks us to find a chain of six numbers that overlap and
 * all belong to one of six sequences.
 * </p>
 * <p>
 * The solution first enumerates the sequences for each lookup later. This is trivially fast and simplifies the
 * algorithm. It then recurses through each array of calculated numbers and looks for matches in each of the other
 * arrays where the numbers overlap. If it finds one, it recurses and repeats the process, looking for another value in
 * a third array that overlaps with the second. It does this until it finds a match: there is only one with four digits,
 * and the sequences only contain four-digit numbers.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "61")
public class Solver_0061
implements ISolver {

  private static final IntPredicate RANGE_FILTER = (i -> i >= 1_000 && i < 10_000);

  private final int[][] values = new int[6][];

  @Override
  public long getExpectedResult() {
    return 28_684;
  }

  @Override
  public long getActualResult() {
    // Triangulars
    values[0] = IntStream.range(1, 500).map(i -> i * (i + 1) / 2).filter(RANGE_FILTER).toArray();
    // Squares
    values[1] = IntStream.range(1, 500).map(i -> i * i).filter(RANGE_FILTER).toArray();
    // Pentagonals
    values[2] = IntStream.range(1, 500).map(i -> i * (3 * i - 1) / 2).filter(RANGE_FILTER).toArray();
    // Hexagonals
    values[3] = IntStream.range(1, 500).map(i -> i * (2 * i - 1)).filter(RANGE_FILTER).toArray();
    // Heptagonals
    values[4] = IntStream.range(1, 500).map(i -> i * (5 * i - 3) / 2).filter(RANGE_FILTER).toArray();
    // Octagonals
    values[5] = IntStream.range(1, 500).map(i -> i * (3 * i - 2)).filter(RANGE_FILTER).toArray();

    return Arrays.stream(process(new boolean[values.length], new int[0])).sum();
  }

  private int[] process(final boolean[] processed, final int[] chain) {
    if (chain.length == values.length) {
      if (match(chain[values.length - 1], chain[0])) {
        return chain;
      }
      return new int[0];
    }
    // Iterate over the top-level arrays that are not being iterated further up the stack.
    for (int i = 0; i < processed.length; ++i) {
      if (!processed[i]) {
        // Iterate over the new current array.
        for (int j = 0; j < values[i].length; ++j) {
          // Nothing in the chain, OR the next value is compatible with the end of the chain and the value is not
          // already in the chain.
          if (chain.length == 0
            || (match(chain[chain.length - 1], values[i][j]) && Arrays.binarySearch(chain, values[i][j]) < 0)) {
            // Found a new candidate, recurse
            final boolean[] newProcessed = processed.clone();
            newProcessed[i] = true;
            final int[] newChain = new int[chain.length + 1];
            System.arraycopy(chain, 0, newChain, 0, chain.length);
            newChain[newChain.length - 1] = values[i][j];
            int[] result = process(newProcessed, newChain);
            if (result.length > 0) {
              return result;
            }
          }
        }
      }
    }
    return new int[0];
  }

  private boolean match(final int a, final int b) {
    return a % 100 == b / 100;
  }

}
