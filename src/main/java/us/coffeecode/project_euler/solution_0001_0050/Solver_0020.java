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

import static java.math.BigInteger.ONE;

import java.math.BigInteger;
import java.util.stream.LongStream;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=20">Problem 20</a> asks us to find the sum of the digits of
 * <code>100!</code>
 * </p>
 * <p>
 * This is a really big number, larger than will fit into any Java primitive type without loss of precision. Use
 * BigInteger instead. Simply perform some stream operations to multiply the numbers into a BigInteger, then add up its
 * digits in a second stream operation.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "20")
public class Solver_0020
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 648;
  }

  @Override
  public long getActualResult() {
    // Calculate the factorial.
    final BigInteger value =
      LongStream.rangeClosed(2, 100).mapToObj(BigInteger::valueOf).reduce(ONE, (x, y) -> x.multiply(y));
    // Add up the digits.
    return value.toString().codePoints().mapToLong(c -> c - '0').sum();
  }

}
