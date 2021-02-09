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
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.InjectionConfiguration;

/**
 * <p>
 * Contains code common to solution test harnesses.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
abstract class AbstractTestSolvers {

  private static ConfigurableApplicationContext context;

  @BeforeClass
  public static void before() {
    context = new AnnotationConfigApplicationContext(InjectionConfiguration.class);
  }

  @AfterClass
  public static void after() {
    context.close();
  }

  protected void test(final Class<? extends ISolver> impl) throws Exception {
    final String problem = impl.getAnnotation(Component.class).value();
    final ISolver solver = context.getBean(impl);
    final long expected = solver.getExpectedResult();
    final long actual = solver.getActualResult();
    Assert.assertEquals("Incorrect result for problem " + problem, expected, actual);
  }

}
