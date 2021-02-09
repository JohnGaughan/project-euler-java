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
 * <a href="https://projecteuler.net/problem=17">Problem 17</a> asks us to count the total number of letters used when
 * writing the first thousand numbers in English form using words instead of numbers.
 * </p>
 * <p>
 * The naive approach would construct strings for each number: however, we do not care about the contents of each
 * string, only its length. I started by separating each category of words as seen in the code, and finding the sum of
 * each group. Then I analyze the English rules for constructing words to represent numbers, and calculate how many
 * times each group must appear. Add all of this up, and we have the answer to the problem.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "17")
public class Solver_0017
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 21_124;
  }

  @Override
  public long getActualResult() {
    // Set up each group of words and calculate their lengths.
    final int one = "one".length();

    final int ones = IntStream.of(one, "two".length(), "three".length(), "four".length(), "five".length(),
      "six".length(), "seven".length(), "eight".length(), "nine".length()).sum();

    final int teens =
      IntStream.of("ten".length(), "eleven".length(), "twelve".length(), "thirteen".length(), "fourteen".length(),
        "fifteen".length(), "sixteen".length(), "seventeen".length(), "eighteen".length(), "nineteen".length()).sum();

    final int tens = IntStream.of("twenty".length(), "thirty".length(), "forty".length(), "fifty".length(),
      "sixty".length(), "seventy".length(), "eighty".length(), "ninety".length()).sum();

    final int hundred = "hundred".length();

    final int thousand = "thousand".length();

    final int and = "and".length();

    // Ones appear nine times per hundred in the ones column, times ten hundreds (including zero hundreds). They also
    // appear one hundred times each in the hundreds column.
    int sum = 190 * ones;

    // Teens appear a total of ten times, once per hundred.
    sum += 10 * teens;

    // Tens appear ten times per hundred, times ten hundreds.
    sum += 100 * tens;

    // The word "hundred" appears 900 times, from 100 to 999, inclusive.
    sum += 900 * hundred;

    // The word "and" appears 891 times: once for each hundred, minus the nine even hundreds that end with 00
    sum += 891 * and;

    // Finally, add the lone "one thousand."
    sum += one + thousand;

    return sum;
  }

}
