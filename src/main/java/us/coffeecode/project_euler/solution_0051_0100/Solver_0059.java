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
package us.coffeecode.project_euler.solution_0051_0100;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=59">Problem 509</a> asks us to decrypt a file encrypted using XOR and a
 * three-character key which is not provided: we need to perform analysis to guess the key.
 * </p>
 * <p>
 * The algorithm reads in the file, which is a comma-delimited series of bytes representing characters. Since the key
 * has three characters, it then splits the cipher text into three pieces, interwoven so each piece corresponds with one
 * byte of the key. Next, we analyze the input. While the letter "e" is the most common letter in the English language
 * for most text, the space between words is actually the most common character. Find the most common byte in each piece
 * and xor with space to get that byte of the key. Once this is all done, decrypt the cipher text and find the sum of
 * the ASCII values in the original text.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "59")
public class Solver_0059
implements ISolver {

  @Override
  public long getExpectedResult() {
    return 129_448;
  }

  @Override
  public long getActualResult() {
    final byte[] cipher = getInput();
    final byte[][] splitCipher = split(cipher);
    final byte[] key = new byte[splitCipher.length];
    for (int i = 0; i < splitCipher.length; ++i) {
      final byte[][] frequency = getFrequency(splitCipher[i]);
      key[i] = analyze(frequency);
    }
    final byte[] plaintext = decrypt(cipher, key);
    int sum = 0;
    for (int character : plaintext) {
      sum += character;
    }
    return sum;
  }

  /** Perform a frequency analysis and get the key for this data. */
  private byte analyze(final byte[][] frequency) {
    byte mostFrequent = frequency[0][0];
    // "e" is the most frequent letter, but " " is the most frequent _character_.
    return (byte) (mostFrequent ^ (byte) ' ');
  }

  /** Decrypt using XOR encryption. */
  private byte[] decrypt(final byte[] cipher, final byte[] key) {
    byte[] plaintext = new byte[cipher.length];
    for (int i = 0; i < plaintext.length; ++i) {
      plaintext[i] = (byte) (cipher[i] ^ key[i % key.length]);
    }
    return plaintext;
  }

  /**
   * Given ciphertext, analyze the character frequency. Get a list of integer pairs: the first element is the cipher
   * character, the second element is the number of times it appears. The list is ordered in descending order of
   * character frequency.
   */
  private byte[][] getFrequency(final byte[] cipher) {
    final List<byte[]> result = new ArrayList<>();
    for (final byte character : cipher) {
      byte[] characterEntry = getCharacterEntry(result, character);
      ++characterEntry[1];
    }
    Collections.sort(result, (a, b) -> {
      // Note: this is reversed on purpose.
      return Byte.compare(b[1], a[1]);
    });
    return result.toArray(new byte[result.size()][]);
  }

  /** Given a list of frequencies, find the entry matching the given character. */
  private byte[] getCharacterEntry(final List<byte[]> frequencies, final byte character) {
    byte[] result = null;
    for (final byte[] candidate : frequencies) {
      if (candidate[0] == character) {
        result = candidate;
        break;
      }
    }
    if (result == null) {
      frequencies.add(result = new byte[2]);
      result[0] = character;
    }
    return result;
  }

  /** Split the cipher into three pieces, since we know the key is three characters. */
  private byte[][] split(final byte[] cipher) {
    final byte[][] splitCipher = new byte[3][];
    final int remainder = cipher.length % 3;
    if (remainder == 0) {
      splitCipher[0] = new byte[cipher.length];
      splitCipher[1] = new byte[cipher.length];
    }
    else if (remainder == 1) {
      splitCipher[0] = new byte[cipher.length + 1];
      splitCipher[1] = new byte[cipher.length];
    }
    else if (remainder == 2) {
      splitCipher[0] = new byte[cipher.length + 1];
      splitCipher[1] = new byte[cipher.length + 1];
    }
    splitCipher[2] = new byte[cipher.length];
    for (int i = 0; i < cipher.length; ++i) {
      splitCipher[i % 3][i / 3] = cipher[i];
    }
    return splitCipher;
  }

  private static final Pattern SEPARATOR = Pattern.compile(",");

  /** Get the cipher from the file, where each character is represented by a byte. */
  private byte[] getInput() {
    try {
      final String[] bytes = SEPARATOR.split(Files.readString(getInputPath()).trim());
      final byte[] cipher = new byte[bytes.length];
      for (int i = 0; i < cipher.length; ++i) {
        cipher[i] = Byte.parseByte(bytes[i]);
      }
      return cipher;
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
