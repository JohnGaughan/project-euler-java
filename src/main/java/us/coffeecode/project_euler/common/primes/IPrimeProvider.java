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
package us.coffeecode.project_euler.common.primes;

/**
 * Interface for an object that provides prime numbers via implementation-defined means.<br>
 * <br>
 * Copyright (c) 2017 John Gaughan<br>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 * @since Jan 29, 2017
 */
public interface IPrimeProvider {

  /**
   * Provide the requested quantity of prime numbers. These will be the first _n_ prime numbers, starting with 2.
   *
   * @param quantityOfPrimes the desired number of prime numbers to provide.
   * @return an array containing prime numbers.
   * @throws Exception if an error occurs while providing the primes.
   */
  int[] getFirstNPrimes(final int quantityOfPrimes);

  /**
   * Provide all prime numbers less than or equal to the provided ceiling value. The ceiling may be prime or composite:
   * if composite, the maximum value will be lower than the ceiling. If the implementation is unable to provide prime
   * numbers up to the ceiling, fewer primes may be returned such that there are prime numbers between the maximum value
   * in the returned array and the ceiling.
   *
   * @param ceiling the desired maximum prime value.
   * @return an array containing prime numbers.
   * @throws Exception if an error occurs while providing the primes.
   */
  int[] getPrimesEqualToOrLessThan(final int ceiling);
}
