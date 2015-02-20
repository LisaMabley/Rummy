package com.Lisa;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        // Initialize hands
        Player humanPlayer = new Player();
        Player computerAiPlayer = new Player();

        // Opening deal
        humanPlayer = newDeck.dealCards(10, humanPlayer);
        computerAiPlayer = newDeck.dealCards(10, computerAiPlayer);

        // Output player cards
        System.out.println("Your hand:");
        for (Card card : humanPlayer.getHand()) {
            System.out.println(card.getName());
        }

        // While whatever startRound()
        // When whatever calculateScore()
        // Display score
        // Ask if player wants to play again
    }

    public static void startRound() {
        // check if discard pile is empty
        // interactWithHuman()
        // aiStrategize()
        // check if any player's hand is empty (they have won)
    }

    public static void interactWithHuman() {
        // Display human's hand
        // Display top card in discard pile
        // Display all runs and books on table
        // Display options: draw from deck, draw from discard, meld (create new run or book), lay off (add to existing run or book)
        // Receive player input
    }

    public static void aiStrategize() {
        // if whatever, do whatever
    }

    public static void calculateScore() {
        // TODO
    }
}
