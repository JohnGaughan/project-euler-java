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
    final boolean actual = new PermutationCheck().check(0, 1);
    Assert.assertFalse(actual);
  }

  @Test
  public void test2() {
    final boolean actual = new PermutationCheck().check(12, 21);
    Assert.assertTrue(actual);
  }

  @Test
  public void test3() {
    final boolean actual = new PermutationCheck().check(12, 12);
    Assert.assertTrue(actual);
  }

  @Test
  public void test4() {
    final boolean actual = new PermutationCheck().check(10, 100);
    Assert.assertFalse(actual);
  }

  @Test
  public void test5() {
    final boolean actual = new PermutationCheck().check(65, 56);
    Assert.assertTrue(actual);
  }

}
