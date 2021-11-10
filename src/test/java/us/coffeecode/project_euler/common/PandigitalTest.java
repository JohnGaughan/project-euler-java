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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    final boolean actual = new Pandigital().test(0);
    Assertions.assertFalse(actual);
  }

  @Test
  public void test_1() {
    final boolean actual = new Pandigital().test(1);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test_6() {
    final boolean actual = new Pandigital().test(6);
    Assertions.assertFalse(actual);
  }

  @Test
  public void test_10() {
    final boolean actual = new Pandigital().test(10);
    Assertions.assertFalse(actual);
  }

  @Test
  public void test_12() {
    final boolean actual = new Pandigital().test(12);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test_21() {
    final boolean actual = new Pandigital().test(21);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test_76() {
    final boolean actual = new Pandigital().test(76);
    Assertions.assertFalse(actual);
  }

  @Test
  public void test_101() {
    final boolean actual = new Pandigital().test(101);
    Assertions.assertFalse(actual);
  }

  @Test
  public void test_123() {
    final boolean actual = new Pandigital().test(123);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test_231() {
    final boolean actual = new Pandigital().test(231);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test_321() {
    final boolean actual = new Pandigital().test(321);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test_123456789() {
    final boolean actual = new Pandigital().test(123_456_789);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test_428379561() {
    final boolean actual = new Pandigital().test(428_379_561);
    Assertions.assertTrue(actual);
  }

}
