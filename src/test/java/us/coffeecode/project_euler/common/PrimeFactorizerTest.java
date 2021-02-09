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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import us.coffeecode.project_euler.InjectionConfiguration;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * Test harness for {@link PrimeFactorizer}
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
public final class PrimeFactorizerTest {

  private static int[] primes;

  @BeforeClass
  public static void before() throws Exception {
    try (
    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(InjectionConfiguration.class)) {
      final IPrimeProvider primeProvider = context.getBean(IPrimeProvider.class);
      primes = primeProvider.getPrimesEqualToOrLessThan(1_000_000);
    }
  }

  /** Performs a deep equality check on two lists of integer arrays. */
  private boolean equals(final List<int[]> a, final List<int[]> b) {
    if (a.size() != b.size()) {
      return false;
    }
    for (int i = 0; i < a.size(); ++i) {
      if (!Arrays.equals(a.get(i), b.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Test
  public void test_0() {
    final PrimeFactorizer factorizer = new PrimeFactorizer();
    final List<int[]> expected = new ArrayList<>();
    final List<int[]> actual = factorizer.factor(primes, 0);
    Assert.assertTrue(equals(expected, actual));
  }

  @Test
  public void test_1() {
    final PrimeFactorizer factorizer = new PrimeFactorizer();
    final List<int[]> expected = new ArrayList<>();
    final List<int[]> actual = factorizer.factor(primes, 1);
    Assert.assertTrue(equals(expected, actual));
  }

  @Test
  public void test_2() {
    final PrimeFactorizer factorizer = new PrimeFactorizer();
    final List<int[]> expected = new ArrayList<>();
    expected.add(new int[] { 2, 1 });
    final List<int[]> actual = factorizer.factor(primes, 2);
    Assert.assertTrue(equals(expected, actual));
  }

  @Test
  public void test_6() {
    final PrimeFactorizer factorizer = new PrimeFactorizer();
    final List<int[]> expected = new ArrayList<>();
    expected.add(new int[] { 2, 1 });
    expected.add(new int[] { 3, 1 });
    final List<int[]> actual = factorizer.factor(primes, 6);
    Assert.assertTrue(equals(expected, actual));
  }

  @Test
  public void test_12() {
    final PrimeFactorizer factorizer = new PrimeFactorizer();
    final List<int[]> expected = new ArrayList<>();
    expected.add(new int[] { 2, 2 });
    expected.add(new int[] { 3, 1 });
    final List<int[]> actual = factorizer.factor(primes, 12);
    Assert.assertTrue(equals(expected, actual));
  }

  @Test
  public void test_17() {
    final PrimeFactorizer factorizer = new PrimeFactorizer();
    final List<int[]> expected = new ArrayList<>();
    expected.add(new int[] { 17, 1 });
    final List<int[]> actual = factorizer.factor(primes, 17);
    Assert.assertTrue(equals(expected, actual));
  }

  @Test
  public void test_24() {
    final PrimeFactorizer factorizer = new PrimeFactorizer();
    final List<int[]> expected = new ArrayList<>();
    expected.add(new int[] { 2, 3 });
    expected.add(new int[] { 3, 1 });
    final List<int[]> actual = factorizer.factor(primes, 24);
    Assert.assertTrue(equals(expected, actual));
  }

}
