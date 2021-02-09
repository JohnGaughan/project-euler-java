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
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=22">Problem 22</a> asks us to sort a list of words and calculate a value
 * for each word. This value is the sum of its letters positions in the alphabet times the word's position in the list.
 * Finally, sum the values of all the words.
 * </p>
 * <p>
 * The algorithm reads in the strings and sorts them. It then converts each string to an integer value. Then iterate
 * these individual values and combine them into an overall result based on their position in the array.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "22")
public class Solver_0022
implements ISolver {

  private static final Path INPUT = Path.of("input-problem-0022.txt");

  @Override
  public long getExpectedResult() {
    return 871_198_282;
  }

  @Override
  public long getActualResult() {
    int result = 0;
    final int[] scores = getInput();
    for (int i = 0; i < scores.length; ++i) {
      result += (i + 1) * scores[i];
    }
    return result;
  }

  public int[] getInput() {
    try {
      return Files.readAllLines(INPUT).stream().sorted().collect(Collectors.toList()).stream().mapToInt(
        s -> s.codePoints().map(ch -> ch - 'A' + 1).sum()).toArray();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
