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
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import us.coffeecode.project_euler.ISolver;
import us.coffeecode.project_euler.common.InputPath;

/**
 * <p>
 * <a href="https://projecteuler.net/problem=54">Problem 54</a> asks us to compare 1,000 pairs of poker hands to see who
 * wins.
 * </p>
 * <p>
 * This is a somewhat interesting problem that can be solved in one of several ways. As usual with programming
 * challenges like Project Euler, intermediate values are rarely needed. We actually do not need to know what the cards
 * are in order to solve the puzzle: instead, we just need to know how to rank poker hands. This algorithm parses the
 * input by converting each line into two poker hands consisting of arrays of cards, and each card is an array
 * containing the rank and suit. However, they are converted to numbers: we only care that one card outranks another, or
 * has the same suit (whatever the suit may be).
 * </p>
 * <p>
 * From here we sort each hand and assign it a score. The score is a long integer that ranks that hand compared to all
 * other poker hands. Since suits don't matter at all in poker other than determining a flush, we store the ranks (e.g.
 * joker, ace, 5) in the low 20 bits of the score. We then move up from there in order of what hand beats another hand.
 * A hand with only a high card will have those 20 bits set, while a straight flush will have the highest bits set,
 * making it compare greater than e.g. a full house through integer comparison. The only tricky part is using bit
 * shifts: anything shifted to bit 32 or greater must be a long or cast to a long to avoid overflow. This is why all the
 * card arrays are defined as longs instead of ints. It makes the code a little cleaner and easier to read.
 * </p>
 * <p>
 * I also took care with variable naming. Most loop control variables have specific names instead of generic
 * single-letter throw-away names. I also added constants for the innermost arrays for the rank and suit. Between the
 * two of these improvements from most other solution, it makes the triple-nested arrays much easier to read and
 * understand what is going on.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Component(value = "54")
public class Solver_0054
implements ISolver {

  private static final Path INPUT = InputPath.of("input-problem-0054.txt");

  private static final Pattern SEPARATOR = Pattern.compile(" ");

  private static final int RANK = 0;

  private static final int SUIT = 1;

  @Override
  public long getExpectedResult() {
    return 376;
  }

  @Override
  public long getActualResult() {
    int player1wins = 0;
    for (final long[] hands : getInput()) {
      if (hands[0] > hands[1]) {
        ++player1wins;
      }
    }
    return player1wins;
  }

  /**
   * Get the program input. The outer array is all of the hands, corresponding to one line each in the input file. The
   * inner array is a two-element array with the scores of the two poker hands, not the actual card data.
   */
  private long[][] getInput() {
    try {
      return Files.readAllLines(INPUT).stream().map(s -> convert(SEPARATOR.split(s))).map(this::score).toArray(
        long[][]::new);
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Given a string representation of one line (two hands) in the input file, convert it into an array of card data. The
   * outermost array contains two hands, for players 1 and 2. The middle array contains five elements for their cards.
   * The innermost array contains two elements for the rank (e.g. queen) and suit (e.g. heart) of each card.
   */
  private long[][][] convert(final String[] cards) {
    // Cards come in as ten strings, five for each hand. Split them.
    final long[][][] result = new long[2][cards.length >> 1][2];

    for (int hand = 0; hand < result.length; ++hand) {
      for (int card = 0; card < result[hand].length; ++card) {
        // Set the rank.
        final int rank = cards[5 * hand + card].codePointAt(RANK);
        result[hand][card][RANK] = switch (rank) {
          case '2' -> 0;
          case '3' -> 1;
          case '4' -> 2;
          case '5' -> 3;
          case '6' -> 4;
          case '7' -> 5;
          case '8' -> 6;
          case '9' -> 7;
          case 'T' -> 8;
          case 'J' -> 9;
          case 'Q' -> 10;
          case 'K' -> 11;
          case 'A' -> 12;
          default -> throw new IllegalArgumentException(Character.toString(rank));
        };

        // Set the suit.
        final int suit = cards[5 * hand + card].codePointAt(SUIT);
        result[hand][card][SUIT] = switch (suit) {
          case 'C' -> 0;
          case 'D' -> 1;
          case 'H' -> 2;
          case 'S' -> 3;
          default -> throw new IllegalArgumentException(Character.toString(suit));
        };
      }

      // Sort the cards, low to high.
      Arrays.sort(result[hand], (card1, card2) -> card2[RANK] < card1[RANK] ? 1 : (card2[RANK] > card1[RANK] ? -1 : 0));
    }
    return result;
  }

  /** Get a score that serves as an absolute ranking of this hand against all other possible hands. */
  private long[] score(final long[][][] hands) {
    long[] scores = new long[hands.length];

    for (int hand = 0; hand < scores.length; ++hand) {
      // Construct an array, one element for each rank (A, K, 7, etc), with the number of matching cards as the value.
      final int[] rankCounts = new int[13];
      for (int rank = 0; rank < rankCounts.length; ++rank) {
        for (int card = 0; card < hands[hand].length; ++card) {
          if (hands[hand][card][RANK] == rank) {
            ++rankCounts[rank];
          }
        }
      }

      // Get whether this hand has a flush.
      final boolean flush =
        (hands[hand][0][SUIT] == hands[hand][1][SUIT]) && (hands[hand][0][SUIT] == hands[hand][2][SUIT])
          && (hands[hand][0][SUIT] == hands[hand][3][SUIT]) && (hands[hand][0][SUIT] == hands[hand][4][SUIT]);

      // Get whether this hand has a straight.
      final boolean straight =
        (hands[hand][0][RANK] + 1 == hands[hand][1][RANK]) && (hands[hand][1][RANK] + 1 == hands[hand][2][RANK])
          && (hands[hand][2][RANK] + 1 == hands[hand][3][RANK]) && (hands[hand][3][RANK] + 1 == hands[hand][4][RANK]);

      // Get the rank of the three and four of a kind, if present.
      long threeOfAKind = -1;
      long fourOfAKind = -1;
      for (int rank = 0; rank < rankCounts.length; ++rank) {
        if (rankCounts[rank] == 3) {
          threeOfAKind = rank;
        }
        if (rankCounts[rank] == 4) {
          fourOfAKind = rank;
        }
      }

      // Get each of the pairs, if present.
      long lowPair = -1;
      long highPair = -1;
      for (int rank = 0; rank < rankCounts.length; ++rank) {
        if (rankCounts[rank] == 2) {
          if (lowPair < 0) {
            lowPair = rank;
          }
          else {
            highPair = rank;
          }
        }
      }

      // *****************************************************************************************
      // Now there is enough information to start building the score.

      // Bits 0-19 are the card ranks, low to high. This way in the case of a tie, it breaks automatically.
      scores[hand] |= hands[hand][0][RANK];
      scores[hand] |= hands[hand][1][RANK] << 4;
      scores[hand] |= hands[hand][2][RANK] << 8;
      scores[hand] |= hands[hand][3][RANK] << 12;
      scores[hand] |= hands[hand][4][RANK] << 16;

      // Bits 20-23 are the rank of the single pair.
      if (lowPair > -1) {
        scores[hand] |= lowPair << 20;
      }

      // Bits 24-27 are the rank of the higher second pair.
      if (highPair > -1) {
        scores[hand] |= highPair << 24;
      }

      // Bits 28-31 are the rank of the three of a kind.
      if (threeOfAKind > -1 && lowPair < 0) {
        scores[hand] |= threeOfAKind << 28;
      }

      // Bits 32-35 are the value of the high card in a straight.
      if (straight) {
        scores[hand] |= hands[hand][4][RANK] << 32;
      }

      // Bit 36 is set if there is a flush.
      if (flush) {
        scores[hand] |= 1 << 36;
      }

      // Bits 37-40 are the value of the three of a kind in a full house. Its pair is in 20-23 (already set).
      if (threeOfAKind > -1 && lowPair > -1) {
        scores[hand] |= threeOfAKind << 37;
      }

      // Bits 41-44 are the rank of the four of a kind.
      if (fourOfAKind > -1) {
        scores[hand] |= fourOfAKind << 41;
      }

      // Bits 45-48 are the value of the high card in the straight flush (including royal).
      if (straight && flush) {
        scores[hand] |= hands[hand][4][RANK] << 45;
      }
    }

    return scores;
  }

}
