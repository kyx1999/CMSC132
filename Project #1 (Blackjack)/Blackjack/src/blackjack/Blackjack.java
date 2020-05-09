package blackjack;

import java.util.*;

public class Blackjack implements BlackjackEngine {

    /**
     * Constructor you must provide.  Initializes the player's account
     * to 200 and the initial bet to 5.  Feel free to initialize any other
     * fields. Keep in mind that the constructor does not define the
     * deck(s) of cards.
     *
     * @param randomGenerator
     * @param numberOfDecks
     */
    int account;
    int bet;
    int numberOfDecks;
    int gameStatus = GAME_IN_PROGRESS;
    ArrayList<Card> deckOfCards;
    ArrayList<Card> player;
    ArrayList<Card> dealer;
    Random randomGenerator;

    public Blackjack(Random randomGenerator, int numberOfDecks) {
        account = 200;
        bet = 5;
        this.numberOfDecks = numberOfDecks;
        this.randomGenerator = randomGenerator;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void createAndShuffleGameDeck() {
        deckOfCards = new ArrayList<Card>();
        //read the cards to the array list
        for (int i = 0; i < numberOfDecks; i++) {
            for (CardSuit suit : CardSuit.values()) {
                for (CardValue value : CardValue.values()) {
                    deckOfCards.add(new Card(value, suit));
                }
            }
        }
        Collections.shuffle(deckOfCards, randomGenerator);
    }

    public Card[] getGameDeck() {
        int size = deckOfCards.size();
        Card[] cards = new Card[size];
        for (int i = 0; i < size; i++) {
            cards[i] = (Card) deckOfCards.get(i);
        }
        return cards;
    }

    public void deal() {
        createAndShuffleGameDeck();
        player = new ArrayList<Card>();
        dealer = new ArrayList<Card>();
        player.add(deckOfCards.remove(0));
        dealer.add(deckOfCards.remove(0));
        player.add(deckOfCards.remove(0));
        dealer.add(deckOfCards.remove(0));
        dealer.get(0).setFaceDown();
        gameStatus = GAME_IN_PROGRESS;
        account -= bet;
    }

    public Card[] getDealerCards() {
        int size = dealer.size();
        Card[] dealerC = new Card[size];
        for (int i = 0; i < size; i++) {
            dealerC[i] = (Card) dealer.get(i);
        }
        return dealerC;
    }


    public int[] getDealerCardsTotal() {
        int sum = 0;
        for (Card card : dealer) {
            sum += card.getValue().getIntValue();
        }
        if (sum > 21) {
            return null;
        } else {
            int times = Integer.min(Collections.frequency(dealer, new Card(CardValue.Ace, CardSuit.SPADES)) + Collections.frequency(dealer, new Card(CardValue.Ace, CardSuit.DIAMONDS)) + Collections.frequency(dealer, new Card(CardValue.Ace, CardSuit.HEARTS)) + Collections.frequency(dealer, new Card(CardValue.Ace, CardSuit.CLUBS)) + 1, (21 - sum) / 10 + 1);
            int[] dealerT = new int[times];
            dealerT[0] = sum;
            for (int i = 1; i < times; i++) {
                dealerT[i] = dealerT[i - 1] + 10;
            }
            return dealerT;
        }
    }

    public int getDealerCardsEvaluation() {
        int[] arr = getDealerCardsTotal();
        if (arr == null) {
            return BUST;
        }
        if (arr.length == 2 && ((dealer.contains(new Card(CardValue.Ace, CardSuit.SPADES)) || dealer.contains(new Card(CardValue.Ace, CardSuit.DIAMONDS)) || dealer.contains(new Card(CardValue.Ace, CardSuit.HEARTS)) || dealer.contains(new Card(CardValue.Ace, CardSuit.CLUBS))) && (dealer.contains(new Card(CardValue.Jack, CardSuit.SPADES)) || dealer.contains(new Card(CardValue.Jack, CardSuit.DIAMONDS)) || dealer.contains(new Card(CardValue.Jack, CardSuit.HEARTS)) || dealer.contains(new Card(CardValue.Jack, CardSuit.CLUBS)) || dealer.contains(new Card(CardValue.Queen, CardSuit.SPADES)) || dealer.contains(new Card(CardValue.Queen, CardSuit.DIAMONDS)) || dealer.contains(new Card(CardValue.Queen, CardSuit.HEARTS)) || dealer.contains(new Card(CardValue.Queen, CardSuit.CLUBS)) || dealer.contains(new Card(CardValue.King, CardSuit.SPADES)) || dealer.contains(new Card(CardValue.King, CardSuit.DIAMONDS)) || dealer.contains(new Card(CardValue.King, CardSuit.HEARTS)) || dealer.contains(new Card(CardValue.King, CardSuit.CLUBS))))) {
            return BLACKJACK;
        }
        for (int check : arr) {
            if (check == 21) {
                return HAS_21;
            }
        }
        return LESS_THAN_21;
    }

    public Card[] getPlayerCards() {
        int size = player.size();
        Card[] playerC = new Card[size];
        for (int i = 0; i < size; i++) {
            playerC[i] = (Card) player.get(i);
        }
        return playerC;
    }

    public int[] getPlayerCardsTotal() {
        int sum = 0;
        for (Card card : player) {
            sum += card.getValue().getIntValue();
        }
        if (sum > 21) {
            return null;
        } else {
            int times = Integer.min(Collections.frequency(player, new Card(CardValue.Ace, CardSuit.SPADES)) + Collections.frequency(player, new Card(CardValue.Ace, CardSuit.DIAMONDS)) + Collections.frequency(player, new Card(CardValue.Ace, CardSuit.HEARTS)) + Collections.frequency(player, new Card(CardValue.Ace, CardSuit.CLUBS)) + 1, (21 - sum) / 10 + 1);
            int[] playerT = new int[times];
            playerT[0] = sum;
            for (int i = 1; i < times; i++) {
                playerT[i] = playerT[i - 1] + 10;
            }
            return playerT;
        }
    }

    public int getPlayerCardsEvaluation() {
        int[] arr = getPlayerCardsTotal();
        if (arr == null) {
            return BUST;
        }
        if (arr.length == 2 && ((player.contains(new Card(CardValue.Ace, CardSuit.SPADES)) || player.contains(new Card(CardValue.Ace, CardSuit.DIAMONDS)) || player.contains(new Card(CardValue.Ace, CardSuit.HEARTS)) || player.contains(new Card(CardValue.Ace, CardSuit.CLUBS))) && (player.contains(new Card(CardValue.Jack, CardSuit.SPADES)) || player.contains(new Card(CardValue.Jack, CardSuit.DIAMONDS)) || player.contains(new Card(CardValue.Jack, CardSuit.HEARTS)) || player.contains(new Card(CardValue.Jack, CardSuit.CLUBS)) || player.contains(new Card(CardValue.Queen, CardSuit.SPADES)) || player.contains(new Card(CardValue.Queen, CardSuit.DIAMONDS)) || player.contains(new Card(CardValue.Queen, CardSuit.HEARTS)) || player.contains(new Card(CardValue.Queen, CardSuit.CLUBS)) || player.contains(new Card(CardValue.King, CardSuit.SPADES)) || player.contains(new Card(CardValue.King, CardSuit.DIAMONDS)) || player.contains(new Card(CardValue.King, CardSuit.HEARTS)) || player.contains(new Card(CardValue.King, CardSuit.CLUBS))))) {
            return BLACKJACK;
        }
        for (int check : arr) {
            if (check == 21) {
                return HAS_21;
            }
        }
        return LESS_THAN_21;
    }

    public void playerHit() {
        player.add(deckOfCards.remove(0));
        if (getPlayerCardsEvaluation() == BUST) {
            gameStatus = DEALER_WON;
        }
    }

    public void playerStand() {
        dealer.get(0).setFaceUp();
        int[] arr = getDealerCardsTotal();
        while (arr != null && arr[arr.length - 1] < 16) {
            dealer.add(deckOfCards.remove(0));
            arr = getDealerCardsTotal();
        }
        int[] playerVs = getPlayerCardsTotal();
        if (arr == null || playerVs[playerVs.length - 1] > arr[arr.length - 1]) {
            gameStatus = PLAYER_WON;
            account += bet * 2;
        } else if (playerVs[playerVs.length - 1] < arr[arr.length - 1]) {
            gameStatus = DEALER_WON;
        } else {
            gameStatus = DRAW;
            account += bet;
        }
    }

    public void setBetAmount(int amount) {
        bet = amount;
    }

    public int getBetAmount() {
        return bet;
    }

    public void setAccountAmount(int amount) {
        account = amount;
    }

    public int getAccountAmount() {
        return account;
    }

    public int getGameStatus() {
        return gameStatus;
    }
    /* Feel Free to add any private methods you might need */
}
