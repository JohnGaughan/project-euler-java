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

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * Test harness for {@link Pandigital}.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
public final class PandigitalTest {

  @Test
  public void test_0() {
    final boolean actual = new Pandigital().is(0);
    Assert.assertFalse(actual);
  }

  @Test
  public void test_1() {
    final boolean actual = new Pandigital().is(1);
    Assert.assertTrue(actual);
  }

  @Test
  public void test_6() {
    final boolean actual = new Pandigital().is(6);
    Assert.assertFalse(actual);
  }

  @Test
  public void test_10() {
    final boolean actual = new Pandigital().is(10);
    Assert.assertFalse(actual);
  }

  @Test
  public void test_12() {
    final boolean actual = new Pandigital().is(12);
    Assert.assertTrue(actual);
  }

  @Test
  public void test_21() {
    final boolean actual = new Pandigital().is(21);
    Assert.assertTrue(actual);
  }

  @Test
  public void test_76() {
    final boolean actual = new Pandigital().is(76);
    Assert.assertFalse(actual);
  }

  @Test
  public void test_101() {
    final boolean actual = new Pandigital().is(101);
    Assert.assertFalse(actual);
  }

  @Test
  public void test_123() {
    final boolean actual = new Pandigital().is(123);
    Assert.assertTrue(actual);
  }

  @Test
  public void test_231() {
    final boolean actual = new Pandigital().is(231);
    Assert.assertTrue(actual);
  }

  @Test
  public void test_321() {
    final boolean actual = new Pandigital().is(321);
    Assert.assertTrue(actual);
  }

  @Test
  public void test_123456789() {
    final boolean actual = new Pandigital().is(123456789);
    Assert.assertTrue(actual);
  }

  @Test
  public void test_428379561() {
    final boolean actual = new Pandigital().is(428379561);
    Assert.assertTrue(actual);
  }

}
