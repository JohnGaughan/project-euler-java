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
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=35">Problem 35</a> asks us to count the number of primes below 1,000,000
 * where rotating the digits results in also being prime.
 * </p>
 * <p>
 * This is a brute force algorithm that removes some numbers up-front to avoid checking them. Primes greater than ten
 * must end in 1, 3, 7, or 9. Since the prime is rotated and all of its digits end up in the ones' location, filter out
 * all prime numbers that are <i>not</i> composed of only those four digits. Then consume those primes one at a time. If
 * all of its rotations are present then count it and remove its rotations: otherwise, discard it. This ends up counting
 * all of the primes meeting the criteria.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "35")
public class Solver_0035
implements ISolver {

  private static final int LIMIT = 1_000_000;

  private final IPrimeProvider primeProvider;

  @Autowired
  public Solver_0035(final IPrimeProvider provider) {
    primeProvider = provider;
  }

  @Override
  public long getExpectedResult() {
    return 55;
  }

  @Override
  public long getActualResult() {
    /*
     * This removes numbers that cannot possibly have prime rotations. This significantly reduces the number of primes
     * to rotate, while also removing an odd dependency on iteration order due to certain primes containing a zero.
     */
    final Collection<Integer> primes = IntStream.of(primeProvider.getPrimesEqualToOrLessThan(LIMIT)).filter(
      i -> !willBeRotatedComposite(i)).boxed().collect(Collectors.toSet());
    int result = 0;
    // Keep getting a prime and checking for its rotations. Remove the primes checked.
    while (!primes.isEmpty()) {
      final int next = primes.iterator().next().intValue();
      final Collection<Integer> rotations = getRotations(next);
      if (primes.containsAll(rotations)) {
        result += rotations.size();
      }
      primes.removeAll(rotations);
    }
    return result;
  }

  /** Enumerate all rotations of the given integer. */
  private Collection<Integer> getRotations(final int start) {
    final Collection<Integer> results = new LinkedList<>();

    // Count the digits
    int magnitude = 1;
    for (int i = start; i > 9; i /= 10) {
      magnitude *= 10;
    }

    int current = start;
    do {
      results.add(Integer.valueOf(current));
      current = current % 10 * magnitude + current / 10;
    } while (current != start);

    return results;
  }

  /** If the prime is guaranteed to be rotated to a composite, return true. */
  private boolean willBeRotatedComposite(final int prime) {
    int p = prime;
    // Primes below 10 cannot be rotated composite
    if (p > 10) {
      while (p > 0) {
        // Primes must end in 1, 3, 7, or 9. Filter out primes that do not contain only those digits.
        if (p % 5 == 0 || p % 2 == 0) {
          return true;
        }
        p /= 10;
      }
    }
    return false;
  }

}
