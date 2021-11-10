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
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.Triangular;

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

  @Autowired
  private Triangular triangular;

  @Override
  public long getExpectedResult() {
    return 162;
  }

  @Override
  public long getActualResult() {
    final Set<Integer> triangles =
      LongStream.rangeClosed(1, 100).map(triangular).mapToObj(l -> Integer.valueOf((int) l)).collect(Collectors.toSet());
    return getScores().stream().filter(triangles::contains).count();
  }

  private Collection<Integer> getScores() {
    try {
      return Files.readAllLines(getInputPath()).stream().map(
        s -> Integer.valueOf(s.codePoints().map(c -> c - 'A' + 1).sum())).toList();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
