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

import java.util.function.IntPredicate;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Determines if numbers are pandigital, a term used by some Project Euler problems. A pandigital number is a number in
 * decimal form of length <em>n</em>, containing all digits from 1..<em>n</em> exactly once.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component
public class Pandigital
implements IntPredicate {

  @Override
  public boolean test(final int value) {
    final String str = Integer.toString(value);
    final boolean[] digitsFound = new boolean[str.length()];

    // Zeros are only valid if the string is length ten, but zero comes before 1 in ASCII.
    final int offset = str.length() == 10 ? '0' : '1';
    for (int i = 0; i < str.length(); ++i) {
      final int digit = str.charAt(i) - offset;
      // If the number is not pandigital, e.g. 1523, the digit will be out of range of the array.
      if ((0 <= digit) && (digit < digitsFound.length)) {
        digitsFound[digit] = true;
      }
    }

    boolean allDigitsFound = true;
    for (int i = 0; allDigitsFound && (i < digitsFound.length); ++i) {
      allDigitsFound &= digitsFound[i];
    }
    return allDigitsFound;
  }

}
