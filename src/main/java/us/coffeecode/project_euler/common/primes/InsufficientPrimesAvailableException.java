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
 * <p>
 * Exception used to indicate that not enough prime numbers are available to fulfill a request to provide primes.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
public class InsufficientPrimesAvailableException
extends RuntimeException {

  private static final long serialVersionUID = 3691196785253669680L;

  /** Constructs a <code>InsufficientPrimesAvailableException</code>. */
  public InsufficientPrimesAvailableException() {
  }

  /** Constructs a <code>InsufficientPrimesAvailableException</code>. */
  public InsufficientPrimesAvailableException(final String message) {
    super(message);
  }

  /** Constructs a <code>InsufficientPrimesAvailableException</code>. */
  public InsufficientPrimesAvailableException(final Throwable cause) {
    super(cause);
  }

  /** Constructs a <code>InsufficientPrimesAvailableException</code>. */
  public InsufficientPrimesAvailableException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /** Constructs a <code>InsufficientPrimesAvailableException</code>. */
  public InsufficientPrimesAvailableException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
