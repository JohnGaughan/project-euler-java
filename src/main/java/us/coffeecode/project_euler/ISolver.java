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
package us.coffeecode.project_euler;

import java.nio.file.Path;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Interface that represents an object that solves an Euler problem.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
public interface ISolver {

  /**
   * Get the actual result. This should be a calculated value.
   *
   * @return the actual result.
   * @throws Exception if an error occurred during calculation.
   */
  long getActualResult();

  /**
   * Get the expected result.
   *
   * @return the expected result.
   */
  long getExpectedResult();

  /**
   * Get the path to the input file, if one is present.
   *
   * @return path to the optional input file.
   */
  default Path getInputPath() {
    final StringBuilder str = new StringBuilder(32);
    str.append(getClass().getAnnotation(Component.class).value());
    while (str.length() < 4) {
      str.insert(0, '0');
    }
    str.insert(0, "input-problem-");
    str.append(".txt");
    return Path.of("src", "main", "resources", str.toString());
  }

}
