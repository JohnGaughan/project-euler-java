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
 * Test harness for {@link Factorizer}.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
public final class FactorizerTest {

  @Test
  public void test_0() {
    final int[] expected = new int[0];
    final int[] actual = new Factorizer().apply(0);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void test_1() {
    final int[] expected = new int[0];
    final int[] actual = new Factorizer().apply(1);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void test_2() {
    final int[] expected = new int[] { 1 };
    final int[] actual = new Factorizer().apply(2);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void test_4() {
    final int[] expected = new int[] { 1, 2 };
    final int[] actual = new Factorizer().apply(4);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void test_17() {
    final int[] expected = new int[] { 1 };
    final int[] actual = new Factorizer().apply(17);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void test_24() {
    final int[] expected = new int[] { 1, 2, 3, 4, 6, 8, 12 };
    final int[] actual = new Factorizer().apply(24);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void test_100() {
    final int[] expected = new int[] { 1, 2, 4, 5, 10, 20, 25, 50 };
    final int[] actual = new Factorizer().apply(100);
    Assertions.assertArrayEquals(expected, actual);
  }

}
