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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.google.common.collect.Collections2;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=43">Problem 43</a> asks us to find the sum of all pandigital numbers where
 * sequences of digits inside the number are divisible by specific numbers in the problem statement.
 * </p>
 * <p>
 * Brute force works, but there are ways to reduce the search space based on the constraints.
 * </p>
 * <ul>
 * <li>Digit group 456 is divisible by 5, and must be 5: if it is 0, that messes up 678 so it cannot be both divisible
 * by 11 and pandigital.
 * <li>Digit group 7890 has some large divisors, so it is possible to cross-check the digits and narrow down to three
 * possibilities: 2867, 3901, 7289.</li>
 * <li>Next, find the possible values that fit into the part divisible by 7. There are two that fit, removing 3901 from
 * the previous end of the number. Now the two endings for the number are 952867 and 357289.
 * </ul>
 * <p>
 * At this point I took a step back for a moment to reassess the problem. The back half of the number was easier to
 * narrow down because the divisors are larger. Three of the divisors in that half are larger than ten, which if the
 * numbers line up just right, can easily disqualify a candidate number portion. Over several overlapping divisors, this
 * dramatically decreased the possibilities while also removing some digits from being able to appear in the front half
 * with its smaller divisors. The numbers that might appear up front are 3 and 6: the numbers that must appear are 0, 1,
 * and 4. Numbers 2, 5, 7, 8, and 9 cannot appear. On to analyze some more.
 * </p>
 * <p>
 * For the ending 952867: digit 4 must be even, so 0 or 4. If it is 4, there is nothing that fits in digit 3 to make
 * that triad divisible by 3. Therefore, digit 4 is 0. The only other number that fits in digit 3 for divisibility by 3,
 * is 3. That leaves 1 and 4, which work in both of the front digits in either configuration: 4130952867 or 1430952867.
 * </p>
 * <p>
 * For the ending 357289: digit 4 must be even, so 0, 4, or 6. If it is 4, there is nothing that fits in digit 3 to make
 * that triad divisible by 3. If 1 goes in digit 3, then neither 0 nor 6 works in digit 4. In fact, those two numbers
 * must go into digits 3 and 4 in either configuration. Then just like with the previous paragraph, 1 and 4 work in
 * either digits 1 or 2. With two pairs of digits there are four permutations: 1406357289, 1460357289, 4160357289,
 * 4106357289.
 * </p>
 * <p>
 * The six numbers are therefore: 4130952867, 1430952867, 1406357289, 1460357289, 4160357289, and 4106357289. Normally I
 * do not like solving these puzzles by hand, but the time on this one was simply too great. While the solution uses
 * Guava to enumerate the permutations, a better idea would be to calculate them in this program and shortcut the logic
 * once a divisibility rule is violated. TODO: do this as the programmatic solution.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "43")
public class Solver_0043
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 16_695_334_890L;
  }

  @Override
  public long getActualResult() {
    long result = 0;
    final Collection<Byte> numbers =
      IntStream.rangeClosed(0, 9).mapToObj(i -> Byte.valueOf((byte) i)).collect(Collectors.toList());
    for (final List<Byte> digits : Collections2.permutations(numbers)) {
      if (hasProperties(digits)) {
        final long number = toLong(digits);
        result += number;
      }
    }
    return result;
  }

  /** Tests the number to see if it satisfies the necessary properties. */
  private boolean hasProperties(final List<Byte> digits) {
    boolean result = digits.get(3).intValue() % 2 == 0;
    result &= toInteger(digits, 2) % 3 == 0;
    result &= digits.get(5).intValue() == 0 || digits.get(5).intValue() == 5;
    result &= toInteger(digits, 4) % 7 == 0;
    result &= toInteger(digits, 5) % 11 == 0;
    result &= toInteger(digits, 6) % 13 == 0;
    result &= toInteger(digits, 7) % 17 == 0;
    return result;
  }

  /** Convert the digits to a long integer. */
  private long toLong(final List<Byte> digits) {
    long result = digits.get(9).longValue();
    result += digits.get(8).longValue() * 10L;
    result += digits.get(7).longValue() * 100L;
    result += digits.get(6).longValue() * 1_000L;
    result += digits.get(5).longValue() * 10_000L;
    result += digits.get(4).longValue() * 100_000L;
    result += digits.get(3).longValue() * 1_000_000L;
    result += digits.get(2).longValue() * 10_000_000L;
    result += digits.get(1).longValue() * 100_000_000L;
    result += digits.get(0).longValue() * 1_000_000_000L;
    return result;
  }

  /** Convert three digits to a single integer, starting at the provided 0-based first digit number. */
  private int toInteger(final List<Byte> digits, final int firstDigit) {
    return digits.get(firstDigit).intValue() * 100 + digits.get(firstDigit + 1).intValue() * 10
      + digits.get(firstDigit + 2).intValue();
  }

}
