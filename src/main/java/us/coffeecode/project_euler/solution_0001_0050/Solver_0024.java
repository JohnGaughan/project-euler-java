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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=24">Problem 24</a> asks us to find the permutations of the digits zero
 * through nine, order them, and find the millionth term.
 * </p>
 * <p>
 * Calculating all of the permutations, ordering, and iterating them is a lot of work. Instead, we can figure out what
 * the number is by skipping everything before it. While we don't know what those numbers are, we <i>do</i> know how
 * many there are. For example: the number must begin with a two. There are 9! terms that begin with each digit. Since
 * 2*9! is smaller than a million and 3*9! is larger, it must start with the third digit, or two. We can then drill down
 * further for each digit using the same algorithm. Eventually, we are left with the exact permutation that is the
 * answer.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "24")
public class Solver_0024
implements ISolver {

  private static final int DIGIT_COUNT = 10;

  @Override
  public long getExpectedResult() {
    return 2_783_915_460L;
  }

  @Override
  public long getActualResult() {
    long answer = 0;
    int remainder = 999999;

    final List<Integer> numbers = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList());

    for (int i = 1; i < DIGIT_COUNT; ++i) {
      final int factorial = IntStream.rangeClosed(2, DIGIT_COUNT - i).reduce(1, (x, y) -> x * y);
      final int idx = remainder / factorial;
      remainder = remainder % factorial;
      answer = 10 * answer + numbers.get(idx).intValue();
      numbers.remove(idx);
      if (remainder == 0) {
        break;
      }
    }

    // Add the last digit if not previously calculated.
    if (!numbers.isEmpty()) {
      answer = 10 * answer + numbers.get(0).intValue();
    }

    return answer;// Long.parseLong(ordinal.toString());
  }

}
