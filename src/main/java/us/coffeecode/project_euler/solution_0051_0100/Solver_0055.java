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
package us.coffeecode.project_euler.solution_0051_0100;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=55">Problem 55</a> asks us to count the number of Lychrel numbers below ten
 * thousand. For this problem, these are numbers that never form a palindrome through reversing and adding to itself in
 * fifty iterations.
 * </p>
 * <p>
 * This is straightforward enough to implement. There are several caches which each provide a significant performance
 * increase. First, cache whether a number is a palindrome or not. This reduces BigInteger math significantly, which is
 * slow. Next, cache reverses of numbers for the same reason. Finally, cache sequence lengths which avoids roughly half
 * of the search tree.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "55")
public class Solver_0055
implements ISolver {

  private static final int ITERATIONS = 50;

  private final Map<BigInteger, Boolean> palindromes = new HashMap<>();

  private final Map<BigInteger, BigInteger> reverses = new HashMap<>();

  private final Map<BigInteger, Integer> cache = new HashMap<>();

  @Override
  public long getExpectedResult() {
    return 249;
  }

  @Override
  public long getActualResult() {
    long result = 0;
    for (int i = 10; i < 10_000; ++i) {
      if (isLychrel(BigInteger.valueOf(i))) {
        ++result;
      }
    }
    return result;
  }

  private boolean isLychrel(final BigInteger number) {
    return sequenceLength(number, 1) < 0;
  }

  private int sequenceLength(final BigInteger number, final int iterations) {
    final BigInteger next = number.add(reverse(number));
    if (cache.containsKey(next)) {
      return cache.get(next).intValue() + iterations;
    }

    // Found one!
    if (isPalindrome(next)) {
      cache.put(next, Integer.valueOf(0));
      return iterations;
    }

    // Recursed too far, get out.
    if (iterations == ITERATIONS) {
      return -1;
    }

    // Recursive step
    int result = sequenceLength(next, iterations + 1);

    // Not Lychrel
    if (result == -1) {
      return result;
    }

    // Lychrel
    cache.put(next, Integer.valueOf(iterations));

    return cache.get(next).intValue() + iterations;
  }

  private boolean isPalindrome(final BigInteger n) {
    if (!palindromes.containsKey(n)) {
      palindromes.put(n, Boolean.valueOf(n.equals(reverse(n))));
    }
    return palindromes.get(n).booleanValue();
  }

  protected BigInteger reverse(final BigInteger number) {
    if (!reverses.containsKey(number)) {
      BigInteger n = number;
      BigInteger x = BigInteger.ZERO;
      while (n.compareTo(BigInteger.ZERO) > 0) {
        x = BigInteger.TEN.multiply(x);
        x = x.add(n.mod(BigInteger.TEN));
        n = n.divide(BigInteger.TEN);
      }
      reverses.put(number, x);
    }
    return reverses.get(number);
  }

}
