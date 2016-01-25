/**
 Implements a simulation of many games of 21.  Objective is to find
 best holding point for a player, assuming that the dealer always
 holds at 17.

Hold    Dealer    Player    Player
Value   Won       Won       Winnings
-----------------------------------------
10      5364      4092      -2089.0
11      5245      4174      -1691.0
12      5095      4304      -981.0
13      4959      4316      -574.0
14      4893      4304      -376.0
15      4856      4294      -239.0
16      4940      4142      -644.0
17      5001      3997      -1000.0
18      5233      3905      -1477.0
19      5755      3541      -3185.0
20      6625      2871      -6103.0
21      8216      1573      -11713.0
>

 I ran the program several times and the optimal hold value varies
 from 12 to 17. In this instance, the player lost the least money when
 he was holding at 15, but surprisingly, won the most games when he
 was holding at 12.


 @author Wayne Zhang
 */

public class Blackjack {
  public static final int BUSTVAL = 21;
  public static final int PLAYER = 1;
  public static final int DEALER = 2;
  public static final int TIE = 0;
  public static int PLAYER_HOLD = 10;
  public static final int DEALER_HOLD = 17;


  public static void main(String[] args) {

	  for(int i = 1; i<=13;i++){
      Hand abc = new Hand();
      abc.add(new Card(1,10));
      abc.add(new Card(1,i));
      System.out.println(abc.handValue());
    }

    final int MAX_GAMES = 10000;  // number of games to play
    Deck deck = new Deck();

    double winnings = 0; // player's winnings
    int pwin = 0; // number of time player wins
    int dwin = 0; // number of times dealer wins
    double bet = 2; // size of player's bet

    System.out.println("Hold\tDealer\tPlayer\tPlayer");
    System.out.println("Value\tWon\tWon\tWinnings");
    System.out.println("-----------------------------------------");
    // Loop over different player hold values.
    for (int phold = PLAYER_HOLD; phold <= BUSTVAL; phold++) {
      pwin = dwin = 0;
      winnings = 0;

      // play many games
      for (int i = 0; i < MAX_GAMES; i++) {

        double value = playGame(deck,bet,phold);
        winnings += value; // accumulate winnings of player
        if (value > 0) {
          pwin++;
        } else if (value < 0) {
          dwin++;
        }

      } // end of many games loop

      System.out.println(phold + "\t" + dwin + "\t" + pwin + "\t" + winnings);

    }


  }

  /**       Play one game of blackjack with the given deck.       The bet is taken from the player prior to play.
    *
    @param deck the deck of cards

    @param bet the bet to place

    @param playerhold card value player holds at

    @return the winnings/loss of the player.    */

  public static double playGame(Deck deck, double bet,int playerhold) {

    // TODO
    Hand dealerHand = new Hand();
    Hand playerHand = new Hand();

    playerHand.add(getCard(deck));
    dealerHand.add(getCard(deck));
    playerHand.add(getCard(deck));
    dealerHand.add(getCard(deck));// XX  OK to here


    while(playerHand.handValue()<playerhold)
    {
      if(playerHand.handValue()==-1)
        break;
      else
        playerHand.add(getCard(deck));
    }
    while(dealerHand.handValue()<DEALER_HOLD)
    {
      if(playerHand.handValue()==-1||dealerHand.handValue()==-1)
        break;
      else
        dealerHand.add(getCard(deck));
    }


    if(dealerHand.handValue()>playerHand.handValue())
    {
      return -bet;
    }
    else if(dealerHand.handValue()<playerHand.handValue())
    {
      if(playerHand.handValue() == 21)
        return bet+1;
      else
        return bet;
    }
    else
    {
      return 0;
    }

//return 0.0;


  }


  /**
   Get next card in the deck.  Get new shuffled deck if necessary.

   @param deck the deck of cards
   @returns the next card.
   */
  public static Card getCard(Deck deck) {
    if (deck.empty())  {
      deck.reset();
    }
    Card c = deck.dealCard();
    return c;
  }
  public static void playGame(Deck current, int bet)
  {

  }



}
