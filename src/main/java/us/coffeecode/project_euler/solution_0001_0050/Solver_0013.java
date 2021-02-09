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

import static java.math.BigInteger.ZERO;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.InputPath;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=13">Problem 13</a> asks us to find the sum of 100 50-digit numbers, then
 * return the first ten digits.
 * </p>
 * <p>
 * This is a simple summation using BigInteger which can actually store the values.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "13")
public class Solver_0013
implements ISolver {

  private static final Path INPUT = InputPath.of("input-problem-0013.txt");

  @Override
  public long getExpectedResult() {
    return 5_537_376_230L;
  }

  @Override
  public long getActualResult() {
    final BigInteger value = loadInput().stream().reduce(ZERO, (x, y) -> x.add(y));
    final String digits = value.toString().substring(0, 10);
    return Long.parseLong(digits);
  }

  private List<BigInteger> loadInput() {
    try {
      return Files.readAllLines(INPUT).stream().map(BigInteger::new).collect(Collectors.toList());
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
