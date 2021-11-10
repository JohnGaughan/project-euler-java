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
 * Test harness for {@link PermutationCheck}.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
public final class PermutationCheckTest {

  @Test
  public void test1() {
    final boolean actual = new PermutationCheck().test(0, 1);
    Assertions.assertFalse(actual);
  }

  @Test
  public void test2() {
    final boolean actual = new PermutationCheck().test(12, 21);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test3() {
    final boolean actual = new PermutationCheck().test(12, 12);
    Assertions.assertTrue(actual);
  }

  @Test
  public void test4() {
    final boolean actual = new PermutationCheck().test(10, 100);
    Assertions.assertFalse(actual);
  }

  @Test
  public void test5() {
    final boolean actual = new PermutationCheck().test(65, 56);
    Assertions.assertTrue(actual);
  }

}
