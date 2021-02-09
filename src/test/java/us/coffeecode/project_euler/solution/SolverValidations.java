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
package us.coffeecode.project_euler.solution;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.InjectionConfiguration;
import us.coffeecode.project_euler.solution_0001_0050.*;
import us.coffeecode.project_euler.solution_0051_0100.*;

/**
 * <p>
 * Test harness that validates all problem solutions.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
public final class SolverValidations {

  private static ConfigurableApplicationContext context;

  @BeforeClass
  public static void before() {
    context = new AnnotationConfigApplicationContext(InjectionConfiguration.class);
  }

  @AfterClass
  public static void after() {
    context.close();
  }

  private void test(final Class<? extends ISolver> impl) throws Exception {
    final String problem = impl.getAnnotation(Component.class).value();
    final ISolver solver = context.getBean(impl);
    final long expected = solver.getExpectedResult();
    final long actual = solver.getActualResult();
    Assert.assertEquals("Incorrect result for problem " + problem, expected, actual);
  }

  @Test
  public void testSolver0001() throws Exception {
    test(Solver_0001.class);
  }

  @Test
  public void testSolver0002() throws Exception {
    test(Solver_0002.class);
  }

  @Test
  public void testSolver0003() throws Exception {
    test(Solver_0003.class);
  }

  @Test
  public void testSolver0004() throws Exception {
    test(Solver_0004.class);
  }

  @Test
  public void testSolver0005() throws Exception {
    test(Solver_0005.class);
  }

  @Test
  public void testSolver0006() throws Exception {
    test(Solver_0006.class);
  }

  @Test
  public void testSolver0007() throws Exception {
    test(Solver_0007.class);
  }

  @Test
  public void testSolver0008() throws Exception {
    test(Solver_0008.class);
  }

  @Test
  public void testSolver0009() throws Exception {
    test(Solver_0009.class);
  }

  @Test
  public void testSolver0010() throws Exception {
    test(Solver_0010.class);
  }

  @Test
  public void testSolver0011() throws Exception {
    test(Solver_0011.class);
  }

  @Test
  public void testSolver0012() throws Exception {
    test(Solver_0012.class);
  }

  @Test
  public void testSolver0013() throws Exception {
    test(Solver_0013.class);
  }

  @Test
  public void testSolver0014() throws Exception {
    test(Solver_0014.class);
  }

  @Test
  public void testSolver0015() throws Exception {
    test(Solver_0015.class);
  }

  @Test
  public void testSolver0016() throws Exception {
    test(Solver_0016.class);
  }

  @Test
  public void testSolver0017() throws Exception {
    test(Solver_0017.class);
  }

  @Test
  public void testSolver0018() throws Exception {
    test(Solver_0018.class);
  }

  @Test
  public void testSolver0019() throws Exception {
    test(Solver_0019.class);
  }

  @Test
  public void testSolver0020() throws Exception {
    test(Solver_0020.class);
  }

  @Test
  public void testSolver0021() throws Exception {
    test(Solver_0021.class);
  }

  @Test
  public void testSolver0022() throws Exception {
    test(Solver_0022.class);
  }

  @Test
  public void testSolver0023() throws Exception {
    test(Solver_0023.class);
  }

  @Test
  public void testSolver0024() throws Exception {
    test(Solver_0024.class);
  }

  @Test
  public void testSolver0025() throws Exception {
    test(Solver_0025.class);
  }

  @Test
  public void testSolver0026() throws Exception {
    test(Solver_0026.class);
  }

  @Test
  public void testSolver0027() throws Exception {
    test(Solver_0027.class);
  }

  @Test
  public void testSolver0028() throws Exception {
    test(Solver_0028.class);
  }

  @Test
  public void testSolver0029() throws Exception {
    test(Solver_0029.class);
  }

  @Test
  public void testSolver0030() throws Exception {
    test(Solver_0030.class);
  }

  @Test
  public void testSolver0031() throws Exception {
    test(Solver_0031.class);
  }

  @Test
  public void testSolver0032() throws Exception {
    test(Solver_0032.class);
  }

  @Test
  public void testSolver0033() throws Exception {
    test(Solver_0033.class);
  }

  @Test
  public void testSolver0034() throws Exception {
    test(Solver_0034.class);
  }

  @Test
  public void testSolver0035() throws Exception {
    test(Solver_0035.class);
  }

  @Test
  public void testSolver0036() throws Exception {
    test(Solver_0036.class);
  }

  @Test
  public void testSolver0037() throws Exception {
    test(Solver_0037.class);
  }

  @Test
  public void testSolver0038() throws Exception {
    test(Solver_0038.class);
  }

  @Test
  public void testSolver0039() throws Exception {
    test(Solver_0039.class);
  }

  @Test
  public void testSolver0040() throws Exception {
    test(Solver_0040.class);
  }

  @Test
  public void testSolver0041() throws Exception {
    test(Solver_0041.class);
  }

  @Test
  public void testSolver0042() throws Exception {
    test(Solver_0042.class);
  }

  @Test
  public void testSolver0043() throws Exception {
    test(Solver_0043.class);
  }

  @Test
  public void testSolver0044() throws Exception {
    test(Solver_0044.class);
  }

  @Test
  public void testSolver0045() throws Exception {
    test(Solver_0045.class);
  }

  @Test
  public void testSolver0046() throws Exception {
    test(Solver_0046.class);
  }

  @Test
  public void testSolver0047() throws Exception {
    test(Solver_0047.class);
  }

  @Test
  public void testSolver0048() throws Exception {
    test(Solver_0048.class);
  }

  @Test
  public void testSolver0049() throws Exception {
    test(Solver_0049.class);
  }

  @Test
  public void testSolver0050() throws Exception {
    test(Solver_0050.class);
  }

  @Test
  public void testSolver0051() throws Exception {
    test(Solver_0051.class);
  }

  @Test
  public void testSolver0052() throws Exception {
    test(Solver_0052.class);
  }

  @Test
  public void testSolver0053() throws Exception {
    test(Solver_0053.class);
  }

  @Test
  public void testSolver0054() throws Exception {
    test(Solver_0054.class);
  }

  @Test
  public void testSolver0055() throws Exception {
    test(Solver_0055.class);
  }

  @Test
  public void testSolver0056() throws Exception {
    test(Solver_0056.class);
  }

  @Test
  public void testSolver0057() throws Exception {
    test(Solver_0057.class);
  }

  @Test
  public void testSolver0058() throws Exception {
    test(Solver_0058.class);
  }

  @Test
  public void testSolver0059() throws Exception {
    test(Solver_0059.class);
  }

  @Test
  public void testSolver0060() throws Exception {
    test(Solver_0060.class);
  }

  @Test
  public void testSolver0061() throws Exception {
    test(Solver_0061.class);
  }

  @Test
  public void testSolver0062() throws Exception {
    test(Solver_0062.class);
  }

  @Test
  public void testSolver0063() throws Exception {
    test(Solver_0063.class);
  }

  @Test
  public void testSolver0064() throws Exception {
    test(Solver_0064.class);
  }

  @Test
  public void testSolver0065() throws Exception {
    test(Solver_0065.class);
  }

  @Test
  public void testSolver0066() throws Exception {
    test(Solver_0066.class);
  }

  @Test
  public void testSolver0067() throws Exception {
    test(Solver_0067.class);
  }

}
