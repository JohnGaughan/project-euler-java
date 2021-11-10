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

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <p>
 * Prime provider backed by a file cache. If the file cache does not exist when this class needs to return primes, it
 * calls on the {@link SievePrimeProvider} to generate the first 50,000,000 primes and saves them to a file. Then
 * whenever it needs to return primes, it memory maps the file and copies the required number of primes out of it.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
public class FileCachePrimeProvider
implements IPrimeProvider {

  /** Name of the file containing prime numbers. */
  private final Path primeFile;

  public FileCachePrimeProvider(final Path path) {
    primeFile = path;
  }

  @Override
  public int[] getFirstNPrimes(final int quantityOfPrimes) {
    try {
      ensureFileExists();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    if (quantityOfPrimes < 0) {
      throw new IllegalArgumentException("Quantity of primes must be nonnegative.");
    }
    int[] primes = new int[0];
    if (quantityOfPrimes == 0) {
      return primes;
    }
    try (final FileChannel channel = FileChannel.open(primeFile)) {
      final long intsAvailable = channel.size() / Integer.BYTES;
      if (intsAvailable < quantityOfPrimes) {
        throw new InsufficientPrimesAvailableException(
          "Not enough primes available. Requested " + quantityOfPrimes + ", only " + intsAvailable + " available.");
      }
      // This cannot fail due to the check above.
      final int quantityOfInts = (int) intsAvailable;
      primes = new int[Math.min(quantityOfPrimes, quantityOfInts)];
      final MappedByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0, (long) primes.length * Integer.BYTES);
      for (int i = 0; i < primes.length; ++i) {
        primes[i] = buffer.getInt();
      }
    }
    catch (final IOException ex) {
      throw new RuntimeException(ex);
    }
    return primes;
  }

  @Override
  public int[] getPrimesEqualToOrLessThan(final int ceiling) {
    try {
      ensureFileExists();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    // If the ceiling is low enough that there are no primes, return an empty array.
    if (ceiling < 2) {
      return new int[0];
    }

    // Get the number of primes to load.
    int quantityOfPrimes = 0;
    try (FileChannel channel = FileChannel.open(primeFile)) {
      // Determine the size of the file channel in integers that is available.
      long channelSize = Math.min(channel.size(), Integer.MAX_VALUE);
      final MappedByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0, channelSize);

      // Find the ceiling first, to allow allocating the prime array once without resizing.
      while (buffer.getInt() <= ceiling) {
        ++quantityOfPrimes;
      }
    }
    catch (final RuntimeException ex) {
      throw ex;
    }
    catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
    return getFirstNPrimes(quantityOfPrimes);
  }

  private void ensureFileExists() throws IOException {
    if (!Files.exists(primeFile)) {
      // 50,000,000 primes is enough for every problem so far.
      final int[] primes = new SievePrimeProvider().getPrimesEqualToOrLessThan(982_451_653);
      try (final FileChannel channel = FileChannel.open(primeFile, CREATE, WRITE, READ)) {
        ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4 * primes.length);
        for (int prime : primes) {
          buffer.putInt(prime);
        }
      }
    }
  }
}
