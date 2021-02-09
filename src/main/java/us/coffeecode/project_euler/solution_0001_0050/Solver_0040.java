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

import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=40">Problem 40</a> asks us to find specific digits in a sequence and
 * multiply them together.
 * </p>
 * <p>
 * The naive solution would generate the entire sequence and look up the requested digits. However, the sequence is
 * well-defined and consistent. This makes it possible to calculate digits directly. The algorithm has a notion of digit
 * groups, which are numbers of the same length concatenated together. The first step is to determine which group a
 * digit it is in. Next, find the specific number within that group. Based on number lengths, we know exactly what that
 * number is. Finally, find the specific digit within that number.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "40")
public class Solver_0040
implements ISolver {

  private static final int[] INPUT = new int[] { 1, 10, 100, 1_000, 10_000, 100_000, 1_000_000 };

  @Override
  public long getExpectedResult() {
    return 210;
  }

  @Override
  public long getActualResult() {
    return IntStream.of(INPUT).map(this::getDigit).reduce(1, (x, y) -> x * y);
  }

  /** Get the nth digit of the constant. */
  private int getDigit(final int d) {
    final int result;
    if (d < 1) {
      throw new IllegalArgumentException("Argument [" + d + "] out of range [1-7,688,889]");
    }

    // Single digits are the first 9 values and take up 9 total digits (1 - 9).
    else if (d < 10) {
      result = d;
    }

    // Double digits are the next 90 values and take up 180 total digits (10 - 189).
    else if (d < 190) {
      final int offset = d - 9;
      final int number = offset / 2 + 9;
      final int digit = offset % 2;
      if (digit == 0) {
        result = number % 10;
      }
      else if (digit == 1) {
        result = (number + 1) / 10;
      }
      else {
        throw new AssertionError(digit);
      }
    }

    // Triple digits are the next 900 values and take up 2,700 total digits (190 - 2,889).
    else if (d < 2_890) {
      final int offset = d - 189;
      final int number = offset / 3 + 99;
      final int digit = offset % 3;
      if (digit == 0) {
        result = number % 10;
      }
      else if (digit == 1) {
        result = (number + 1) / 100;
      }
      else if (digit == 2) {
        result = ((number + 1) / 10) % 10;
      }
      else {
        throw new AssertionError(digit);
      }
    }

    // Quad digits are the next 9,000 values and take up 36,000 total digits (2,890 - 38,889).
    else if (d < 38_890) {
      final int offset = d - 2_889;
      final int number = offset / 4 + 999;
      final int digit = offset % 4;
      if (digit == 0) {
        result = number % 10;
      }
      else if (digit == 1) {
        result = (number + 1) / 1_000;
      }
      else if (digit == 2) {
        result = ((number + 1) / 100) % 10;
      }
      else if (digit == 3) {
        result = ((number + 1) / 10) % 10;
      }
      else {
        throw new AssertionError(digit);
      }
    }

    // Five digits are the next 90,000 values and take up 450,000 total digits (38,890 - 488,889).
    else if (d < 488_890) {
      final int offset = d - 38_889;
      final int number = offset / 5 + 9_999;
      final int digit = offset % 5;
      if (digit == 0) {
        result = number % 10;
      }
      else if (digit == 1) {
        result = ((number + 1) / 10_000) % 10;
      }
      else if (digit == 2) {
        result = ((number + 1) / 1_000) % 10;
      }
      else if (digit == 3) {
        result = ((number + 1) / 100) % 10;
      }
      else if (digit == 4) {
        result = ((number + 1) / 10) % 10;
      }
      else {
        throw new AssertionError(digit);
      }
    }

    // Six digits are the next 900,000 values and take up 7,200,000 total digits (488,890 - 7,688,889).
    else if (d < 7_688_890) {
      final int offset = d - 488_889;
      final int number = offset / 6 + 99_999;
      final int digit = offset % 6;
      if (digit == 0) {
        result = number % 10;
      }
      else if (digit == 1) {
        result = ((number + 1) / 100_000) % 10;
      }
      else if (digit == 2) {
        result = ((number + 1) / 10_000) % 10;
      }
      else if (digit == 3) {
        result = ((number + 1) / 1_000) % 10;
      }
      else if (digit == 4) {
        result = ((number + 1) / 100) % 10;
      }
      else if (digit == 5) {
        result = ((number + 1) / 10) % 10;
      }
      else {
        throw new AssertionError(digit);
      }
    }

    else {
      throw new IllegalArgumentException("Argument [" + d + "] out of range [1-7,688,889]");
    }

    return result;
  }

}
