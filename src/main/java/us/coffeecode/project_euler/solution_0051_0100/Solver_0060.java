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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=60">Problem 60</a> asks us to find five primes where we can concatenate any
 * two them in any order and the result is prime, and return the sum of those primes.
 * </p>
 * <p>
 * TODO: revisit and make more efficient. Also, the code is ugly and those highly nested loops are bad.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "60")
public class Solver_0060
implements ISolver {

  private static final int CEILING = 9_000;

  final int[] primes;

  public Solver_0060(final IPrimeProvider provider) throws Exception {
    primes = provider.getPrimesEqualToOrLessThan(90_000_000);
  }

  @Override
  public long getExpectedResult() {
    return 26_033;
  }

  @Override
  public long getActualResult() {
    Set<Pair> pairs = getPairs();
    Set<Tuple> solutions = new HashSet<>();
    for (int i = 0; (i < primes.length - 4) && (primes[i] < CEILING); ++i) {
      for (int j = i + 1; (j < primes.length - 3) && (primes[j] < CEILING); ++j) {
        if (pairs.contains(new Pair(primes[i], primes[j]))) {
          for (int k = j + 1; (k < primes.length - 2) && (primes[k] < CEILING); ++k) {
            if (pairs.contains(new Pair(primes[i], primes[k])) && pairs.contains(new Pair(primes[j], primes[k]))) {
              for (int l = j + 1; (l < primes.length - 1) && (primes[l] < CEILING); ++l) {
                if (pairs.contains(new Pair(primes[i], primes[l])) && pairs.contains(new Pair(primes[j], primes[l]))
                  && pairs.contains(new Pair(primes[k], primes[l]))) {
                  for (int m = l + 1; (m < primes.length) && (primes[m] < CEILING); ++m) {
                    if (pairs.contains(new Pair(primes[i], primes[m])) && pairs.contains(new Pair(primes[j], primes[m]))
                      && pairs.contains(new Pair(primes[k], primes[m]))
                      && pairs.contains(new Pair(primes[l], primes[m]))) {
                      solutions.add(new Tuple(primes[i], primes[j], primes[k], primes[l], primes[m]));
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return min(solutions);
  }

  /** Get all pairs of numbers that satisfy the condition in the problem: a+b and b+a are prime. */
  private Set<Pair> getPairs() {
    final Set<Pair> pairs = new HashSet<>();
    for (int i = 0; i < primes.length - 1 && primes[i] < CEILING; ++i) {
      for (int j = i + 1; j < primes.length && primes[j] < CEILING; ++j) {
        if (isPrimePair(primes[i], primes[j])) {
          pairs.add(new Pair(primes[i], primes[j]));
        }
      }
    }
    return pairs;
  }

  private boolean isPrimePair(final int a, final int b) {
    long first = Long.parseLong(Integer.toString(a) + Integer.toString(b));
    long second = Long.parseLong(Integer.toString(b) + Integer.toString(a));
    if (first > Integer.MAX_VALUE || second > Integer.MAX_VALUE) {
      return false;
    }
    return Arrays.binarySearch(primes, (int) first) >= 0 && Arrays.binarySearch(primes, (int) second) >= 0;
  }

  private long min(final Iterable<Tuple> values) {
    int a = Integer.MAX_VALUE;
    for (Tuple v : values) {
      if (v.sum < a) {
        a = v.sum;
      }
    }
    return a;
  }

  private static final class Pair {

    public final int a;

    public final int b;

    private final int hashCode;

    private String toString;

    public Pair(final int aa, final int bb) {
      a = aa;
      b = bb;
      hashCode = Objects.hash(Integer.valueOf(a), Integer.valueOf(b), getClass().getName());
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      else if (!(obj instanceof Pair)) {
        return false;
      }
      Pair o = (Pair) obj;
      return a == o.a && b == o.b;
    }

    @Override
    public int hashCode() {
      return hashCode;
    }

    @Override
    public String toString() {
      if (toString == null) {
        toString = "[" + a + "," + b + "]";
      }
      return toString;
    }
  }

  private static final class Tuple {

    public final int a;

    public final int b;

    public final int c;

    public final int d;

    public final int e;

    public final int sum;

    private final int hashCode;

    private String toString;

    public Tuple(final int aa, final int bb, final int cc, final int dd, final int ee) {
      a = aa;
      b = bb;
      c = cc;
      d = dd;
      e = ee;
      sum = a + b + c + d + e;
      hashCode = Objects.hash(Integer.valueOf(a), Integer.valueOf(c), Integer.valueOf(e), Integer.valueOf(b),
        Integer.valueOf(d), getClass().getName());
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      else if (!(obj instanceof Tuple)) {
        return false;
      }
      final Tuple o = (Tuple) obj;
      return a == o.a && b == o.b && c == o.c && d == o.d && e == o.e;
    }

    @Override
    public int hashCode() {
      return hashCode;
    }

    @Override
    public String toString() {
      if (toString == null) {
        toString = "[" + a + "," + b + "," + c + "," + d + "," + e + ",[=" + sum + "]]";
      }
      return toString;
    }
  }

}
