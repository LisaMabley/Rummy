package com.Lisa;

public class Main {

    public static void main(String[] args) {

        // Welcome human player
        System.out.println("LET'S PLAY RUMMY");

        // Initialize deck
        Deck newDeck = new Deck();

        // Initialize players
        HumanPlayer humanPlayer = new HumanPlayer("You");
        ComputerPlayer computerPlayer = new ComputerPlayer("Your cunning opponent");
        Player[] players = {humanPlayer, computerPlayer};

        // Initialize threshold to win
        int winThreshold;

        // FOR TESTING & DEBUGGING
//        humanPlayer.setScore(30);
//        char suit = 9824;
//
//        for (int x = 10; x < 12; x ++) {
//            Card card = new Card(suit, x);
//            computerPlayer.hand.addCard(card);
//        }
//
//        suit = 9829;
//
//        for (int x = 10; x < 12; x ++) {
//            Card card = new Card(suit, x);
//            computerPlayer.hand.addCard(card);
//        }
//
//        suit = 9830;
//
//        for (int x = 2; x < 4; x ++) {
//            Card card = new Card(suit, x);
//            computerPlayer.hand.addCard(card);
//        }
//
//        for (int x = 5; x < 9; x ++) {
//            Card card = new Card(suit, x);
//            humanPlayer.hand.addCard(card);
//        }
//
//        suit = 9827;
//
//        for (int x = 4; x < 6; x ++) {
//            Card card = new Card(suit, x);
//            computerPlayer.hand.addCard(card);
//        }

        boolean playAgain = true;

        while (playAgain) {
            // Reset win threshold for each new game
            winThreshold = humanPlayer.setWinThreshold();

            // If no player has reached win threshold, deal another round
            while (humanPlayer.getScore() < winThreshold && computerPlayer.getScore() < winThreshold) {

                // Deal 10 cards to each player
                dealRound(players, newDeck);

                Player inactivePlayer = players[1];

                // Players take turns until one of them lays down all cards in their hand
                while (!humanPlayer.handIsEmpty) {
                    for (Player activePlayer : players) {
                        newDeck.draw(activePlayer);
                        if (!activePlayer.handIsEmpty) {
                            newDeck.meld(activePlayer);
                        }
                        if (!activePlayer.handIsEmpty) {
                            newDeck.layOff(activePlayer);
                        }
                        if (!activePlayer.handIsEmpty) {
                            newDeck.discard(activePlayer);
                        }
                        activePlayer.endTurn();

                        if (activePlayer.handIsEmpty) {
                            // If active player's hand is empty, they won the round
                            activePlayer.roundWon(inactivePlayer.roundLost());
                            inactivePlayer = activePlayer;
                            break;
                        }
                        inactivePlayer = activePlayer;
                    }

                    if (inactivePlayer.handIsEmpty) {
                        // If their hand is empty and they passed the
                        // win threshold, they won the game
                        if (inactivePlayer.getScore() >= winThreshold) {
                            // Player has won game
                            System.out.println(inactivePlayer.getNickname() + " won the game!");
                            break;

                        } else {
                            // Player has won round
                            resetForNewRound(newDeck, players);
                            break;
                        }
                    }
                }
            }
            System.out.println("\nWould you like to play again?");
            resetScoresForNewGame(newDeck, players);
            playAgain = humanPlayer.playAgain();
        }
    }

    public static void dealRound(Player[] players, Deck newDeck) {
        // Deal 10 cards to each player
        for (Player player : players) {
            newDeck.dealCards(10, player.getHandGroup());
        }
    }

    public static void resetForNewRound(Deck deck, Player[] players) {
        // Empty players' hands, clear all melds from table, and put discard pile back into deck
        deck.resetDeckForNewRound();
        for (Player player : players) {
            player.resetHandForNewRound();
        }
    }

    public static void resetScoresForNewGame(Deck deck, Player[] players) {
        // Same as new round, but also clears players' scores
        resetForNewRound(deck, players);
        for (Player player : players) {
            player.setScore(0);
        }
    }
}
