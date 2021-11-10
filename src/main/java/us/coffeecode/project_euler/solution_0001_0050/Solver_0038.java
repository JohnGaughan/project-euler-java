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

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.Pandigital;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=38">Problem 38</a> asks us to find the largest 9-digit pandigital number
 * that can be formed by concatenating the product of several other numbers, where those numbers are formed by
 * multiplying one number by a series of integers 1, 2, ... n.
 * </p>
 * <p>
 * We know that there has to be at least two numbers concatenated, since n &gt; 1. We also know the final number must
 * start with nine, since the problem gives us an example value that starts with nine that is lower than the answer.
 * Since the first multiplier is one, this means the base integer must start with a nine. Further, we can restrict the
 * number of digits in the base integer:
 * </p>
 * <ul>
 * <li>If the base integer has one digit, it must be 9, which is the example, which is not the answer.</li>
 * <li>If the base integer has two digits, it will be of the form 9X. The first product will be that number, while the
 * second and third products will have three digits, giving a total of eight digits. The next product will not fit.</li>
 * <li>If the base integer has three digits, it will be of the form 9XX. As with before, we get a three digit then a
 * four digit number, and the next cannot fit.</li>
 * </ul>
 * <p>
 * Therefore, the only possibility is the base integer has four digits and starts with nine (9XXX). Multiplying by two
 * gives a five digit number of the form 18XXX. This means it must be between 9,123 and 9,876. Next, we can start at the
 * high end and iterate down, which will find the highest value first.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "38")
public class Solver_0038
implements ISolver {

  @Autowired
  private Pandigital pandigital;

  @Override
  public long getExpectedResult() {
    return 932_718_654;
  }

  @Override
  public long getActualResult() {
    return IntStream.iterate(9_876, (i -> i >= 9_123), (i -> i - 1)).map(i -> (i * 100_000) + (i << 1)).filter(
      pandigital).findFirst().getAsInt();
  }

}
