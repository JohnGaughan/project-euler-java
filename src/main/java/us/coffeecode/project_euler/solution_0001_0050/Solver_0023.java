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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.Factorizer;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=23">Problem 23</a> asks us to find numbers which are not the sum of two
 * abundant numbers, then sum these numbers.
 * </p>
 * <p>
 * Iterate all numbers that can possibly be abundant and check if they are. If so, save the numbers. Then iterate all
 * the numbers under the limit in the problem and see if they can be the sum of two abundant numbers. If so, mark them.
 * Then iterate all those marks and find the ones that <i>cannot</i> be the sum of two abundant numbers. Add up all of
 * those numbers.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "23")
public class Solver_0023
implements ISolver {

  private static final int LIMIT = 28_123;

  @Autowired
  private Factorizer factorizer;

  @Override
  public long getExpectedResult() {
    return 4_179_871;
  }

  @Override
  public long getActualResult() {
    final boolean[] isSumOfAbundants = getNumbersThatAreSumsOfTwoAbundants();
    int result = 0;
    for (int i = 1; i < isSumOfAbundants.length; ++i) {
      if (!isSumOfAbundants[i]) {
        result += i;
      }
    }
    return result;
  }

  private boolean[] getNumbersThatAreSumsOfTwoAbundants() {
    final int[] abundants = getAbundantNumbers();
    final boolean[] result = new boolean[LIMIT + 1];
    for (int i = 0; i < abundants.length; ++i) {
      for (int j = i; j < abundants.length; ++j) {
        final int sum = abundants[i] + abundants[j];
        if (sum < result.length) {
          result[sum] = true;
        }
        else {
          // Sum can only increase until j resets.
          break;
        }
      }
    }
    return result;
  }

  private int[] getAbundantNumbers() {
    // Magic number: this is the number of ints found. Avoid resizing.
    final List<Integer> abundant = new ArrayList<>(6_961);
    // If 12 is the lower bound, then 28123-12 is the upper bound (add one, exclusive).
    for (int i = 12; i < 28_112; ++i) {
      if (i < IntStream.of(factorizer.apply(i)).sum()) {
        abundant.add(Integer.valueOf(i));
      }
    }
    return abundant.stream().mapToInt(Integer::intValue).toArray();
  }

}
