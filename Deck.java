/**
 * Represents a standard deck of 52 Card objects.
 * @author S. Anderson
 * @version Feb 2014
 */

import java.util.* ;

public class Deck
{
  private Card[] deck; // the cards
  private int numDealt ; // number of cards dealt so far, also indicates current card
  private static Random rand;

  /**
   Create a new 52-card deck that is shuffled.
   */
  public Deck() {
    rand = new Random();
    deck = new Card[Card.NSUITS * Card.NVALUES];
    int i = 0;
    for (int s = 0; s < Card.NSUITS; s++)
      for (int v = Card.MINVAL; v <= Card.MAXVAL; v++)
      deck[i++] = new Card(s,v);

    reset();
  }

  /**
   Return deck to a new fully shuffled state with 52 cards.
   */
  public void reset() {
    numDealt = 0;
    shuffle();
  }

  /**
   Returns true iff the deck is empty.
   */
  public boolean empty() {
    return numDealt == deck.length;
  }

  /**
   Deals one card from top of the deck.  Card is removed.
   Will raise exception if called on an empty deck!!

   @return next card from top of deck
   @throws RuntimeException if the deck is empty
   */
  public Card dealCard() throws java.util.NoSuchElementException {
    if (empty())
      throw new java.util.NoSuchElementException("Too many cards dealt.");
    return deck[numDealt++];
  }

  /**
   Use Knuth's shuffling algorithm to shuffle a deck.
   */
  public void shuffle() {
    int rnum;
    Card tmp;
    if (deck.length - numDealt <= 1) return; // no more than one card left
    for(int i = deck.length - 1; i >= numDealt; i--) {
      // shuffle in array positions from numDealt to deck.length-1
      rnum = numDealt + rand.nextInt(i + 1 - numDealt);
      // swap cards
      tmp = deck[i];
      deck[i] = deck[rnum];
      deck[rnum] = tmp;
    }
  }

  /**
   @return string equivalent of entire deck.
   */
  public String toString() {
    String retval = "";
    for (int i = numDealt; i < deck.length; i++) {
      retval += deck[i].toString() + "\n";
    }
    return retval;
  }


} // end of class Deck
