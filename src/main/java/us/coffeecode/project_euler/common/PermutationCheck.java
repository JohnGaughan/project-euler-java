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
package us.coffeecode.project_euler.common;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Determines if the decimal representations of two numbers are permutations of each other: that is, they contain the
 * same decimal digits but potentially in a different order.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component
public class PermutationCheck {

  public boolean check(final long left, final long right) {
    if (left == right) {
      return true;
    }
    // Count the digits in the first value, then subtract the digits in the second. If the net is zero, they are
    // permutations of each other.
    final int[] digits = new int[10];

    long temp = left;
    while (temp > 0) {
      ++digits[(int) (temp % 10)];
      temp /= 10;
    }

    temp = right;
    while (temp > 0) {
      --digits[(int) (temp % 10)];
      temp /= 10;
    }

    boolean allZeros = true;
    for (int i = 0; allZeros && i < digits.length; ++i) {
      allZeros &= digits[i] == 0;
    }
    return allZeros;
  }

}
