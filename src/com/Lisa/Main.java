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
        newDeck.startDiscardPile(newDeck);

        outputGameStatus(humanPlayer, newDeck);

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

    public static void outputGameStatus(Player human, Deck newDeck) {

        // Output player cards
        System.out.println("YOUR HAND:");
        for (Card card : human.getHand()) {
            outputCardToTerminalInColor(card);
            if (card != human.getHand().getLast()) {
                System.out.print(", ");
            }
        }

        // Display top card in discard pile
        System.out.println("\n\nDISCARD PILE:");
        outputCardToTerminalInColor(newDeck.getDiscardPile().peek());

        // Display all runs and books on table
    }

    public static void outputCardToTerminalInColor(Card card) {

        // Set colors for terminal output
        String ANSI_red = "\u001B[31m";
        String ANSI_reset_color = "\u001B[0m";

        if (card.getSuit() > 9828) {
            System.out.print(ANSI_red + card.getName() + ANSI_reset_color);
        } else {
            System.out.print(card.getName());
        }
    }

    public static void getPlayerAction() {
        // Display options: draw from deck, draw from discard, meld (create new run or book), lay off (add to existing run or book)
        // Receive player input
        System.out.println("");
    }

//    public static void aiStrategize() {
//        // if whatever, do whatever
//    }
//
//    public static void calculateScore() {
//        // TODO
//    }
}
