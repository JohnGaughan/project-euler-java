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
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=42">Problem 42</a> asks us to score words based on adding up the positions
 * of their letters in the alphabet, then counting how many of their scores are triangular numbers.
 * </p>
 * <p>
 * This is a fairly straightforward algorithm that simply iterates the words, scores them, and checks if that score is a
 * triangular number.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "42")
public class Solver_0042
implements ISolver {

  private static final Path INPUT = Path.of("input-problem-0042.txt");

  @Override
  public long getExpectedResult() {
    return 162;
  }

  @Override
  public long getActualResult() {
    final Set<Integer> triangleNumbers = getTriangleNumbers(100);
    int result = 0;
    for (final String word : getWords()) {
      final int value = word.codePoints().map(ch -> ch - 'A' + 1).sum();
      if (triangleNumbers.contains(Integer.valueOf(value))) {
        ++result;
      }
    }
    return result;
  }

  private Set<Integer> getTriangleNumbers(final int amount) {
    final Set<Integer> triangleNumbers = new HashSet<>(amount * 4 / 3);
    for (int i = 1; i <= amount; ++i) {
      triangleNumbers.add(Integer.valueOf(i * (i + 1) / 2));
    }
    return triangleNumbers;
  }

  private Iterable<String> getWords() {
    try {
      return Files.readAllLines(INPUT);
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
