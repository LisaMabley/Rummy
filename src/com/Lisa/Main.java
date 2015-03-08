package com.Lisa;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        // Initialize players
        HumanPlayer humanPlayer = new HumanPlayer("You");
        ComputerPlayer computerPlayer = new ComputerPlayer("Your cunning opponent");
        Player[] players = {humanPlayer, computerPlayer};

        // Opening deal
        openingDeal(players, newDeck);

//        // FOR TESTING & DEBUGGING
//        char suit = 9824;
//
//        for (int x = 4; x < 5; x ++) {
//            Card card = new Card(suit, x);
//            computerPlayer.hand.addCard(card);
//            }
//
//        suit = 9829;
//
//        for (int x = 5; x < 6; x ++) {
//            Card card = new Card(suit, x);
//            computerPlayer.hand.addCard(card);
//        }
//
//        suit = 9830;
//
//        for (int x = 5; x < 7; x ++) {
//            Card card = new Card(suit, x);
//            computerPlayer.hand.addCard(card);
//        }
//
//        suit = 9827;
//
//        for (int x = 4; x < 6; x ++) {
//            Card card = new Card(suit, x);
//            computerPlayer.hand.addCard(card);
//        }

        // Game play
        takeTurns(players, newDeck);
    }

    public static void openingDeal(Player[] players, Deck newDeck) {
        for (Player player : players) {
            newDeck.dealCards(10, player.getHandGroup());
        }
    }

    public static void takeTurns(Player[] players, Deck newDeck) {
        while (!newDeck.getStockPile().isEmpty()) { // For now
            for (Player activePlayer : players) {
                newDeck.draw(activePlayer);
                newDeck.meld(activePlayer);
                newDeck.layOff(activePlayer);
                newDeck.discard(activePlayer);
                activePlayer.endTurn();
            }
        }
        // TODO how does game end?
    }
}
