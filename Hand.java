/*
 Represent one hand of cards in BlackJack.
 @author
 */

public class Hand {
  private Card[] cards;
  private int count;
  public static final int DEFAULT_SIZE = 10;

  public Hand() {
    this.cards = new Card[DEFAULT_SIZE];
    this.count = 0;
  }

  /**
   Add card to hand.
   @param c card to add
   */
  public void add(Card c) {
    if (isFull()) {
      resize(2 * count);
    }
    cards[count] = c;
    count++;
  }

  /** True iff hand is full. */
  public boolean isFull() {
    return count == cards.length;
  }

  public void resize(int newsize) {
    if (newsize <= count) return;
    Card[] newhand = new Card[newsize];
    for (int i = 0; i < count; i++) {
      newhand[i] = this.cards[i];
    }
    this.cards = newhand;
  }
  /**       Determine the best way to evaluate this hand.
    *
    @return  maximum value of this hand that is <= 21, assuming aces are 1 or 10.    */

  public int handValue() {

    // TODO
    int numofAces = 0;
    int totalValue = 0;

    for(int i = 0;i<count;i++){
      if(cards[i].value()==1){
        if(numofAces==1)
          totalValue +=11;
        else
          totalValue +=1;
      }
      else if(cards[i].value()<=10)
        totalValue+= cards[i].value();
      else
        totalValue+=10;
    }
    if(totalValue>21)
      return -1;
    return totalValue;
  }


  //return 0; // just temporary!


}
