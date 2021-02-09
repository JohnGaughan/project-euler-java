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

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.google.common.math.IntMath;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=5">Problem 5</a> asks us to find the smallest number that is divisible by
 * all of the numbers from 1 to 20.
 * </p>
 * <p>
 * The problem is asking for the lowest common multiple (LCM). First, the numbers 1-10 can be omitted since each of them
 * has a multiple in the numbers 11-20: LCM(x, nx) = nx. Next, LCM is commutative and associative: LCM(a, b, c) =
 * LCM(LCM(a, b), c) This means we can take a set of numbers, consume two at a time, and replace them with their LCM.
 * When only one number remains, that is the LCM of the entire set of numbers. This is the programmatic equivalent of
 * reducing a set of numbers via the mathematical equation above.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "5")
public class Solver_0005
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 232_792_560;
  }

  @Override
  public long getActualResult() {
    final Queue<Integer> result = new LinkedList<>();
    for (int i = 11; i <= 20; ++i) {
      result.offer(Integer.valueOf(i));
    }
    while (result.size() > 1) {
      final int a = result.poll().intValue();
      final int b = result.poll().intValue();
      final int lcm = a / IntMath.gcd(a, b) * b;
      result.offer(Integer.valueOf(lcm));
    }
    return result.poll().intValue();
  }

}
