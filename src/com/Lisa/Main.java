package com.Lisa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        // Initialize hands
        Player humanPlayer = new Player();
        Player computerAiPlayer = new Player();

        // Opening deal
        newDeck.dealCards(10, humanPlayer.getHandGroup());
        newDeck.dealCards(10, computerAiPlayer.getHandGroup());
        newDeck.dealCards(1, newDeck.getDiscardPileGroup());

        outputGameStatus(humanPlayer, newDeck);
        draw(humanPlayer, newDeck);

        // While whatever startRound()
        // When whatever calculateScore()
        // Display score
        // Ask if player wants to play again
    }

//    public static void startRound() {
//        // check if discard pile is empty
//        // interactWithHuman()
//        // aiStrategize()
//        // check if any player's hand is empty (they have won)
//    }

    public static void outputGameStatus(Player human, Deck newDeck) {

        // Output player cards
        System.out.println("\nYOUR HAND:");
        for (Card card : human.getHandCards()) {
            outputCardToTerminalInColor(card);
            if (card != human.getHandCards().getLast()) {
                System.out.print(", ");
            }
        }

        // Display top card in discard pile
        System.out.println("\n\nDISCARD PILE:");
        if (newDeck.getDiscardPileCards().isEmpty()) {
            newDeck.dealCards(1, newDeck.getDiscardPileGroup());
        }
        outputCardToTerminalInColor(newDeck.getDiscardPileCards().peek());

        // Display all runs and books on table
        System.out.println("\n");
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

    public static void draw(Player player, Deck newDeck) {
        System.out.println("DRAW:\n");
        int drawFromPile = 0;

        // Get valid response from user
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to draw from the stock pile, or 2 to draw from the discard pile.");

            try {
                drawFromPile = scanner.nextInt();

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (drawFromPile != 1 && drawFromPile != 2) {
                // User input an invalid integer
                continue;
            }

            break;
        }

        // Draw card from selected pile
        switch (drawFromPile) {
            case 1:
                newDeck.drawFromStockPile(player.getHandGroup());
                break;
            case 2:
                newDeck.drawFromDiscardPile(player.getHandGroup());
                break;
        }
        outputGameStatus(player, newDeck);
    }

//    public static void getPlayerAction() {
//        // Display options: draw from deck, draw from discard, meld (create new run or book), lay off (add to existing run or book)
//        // Receive player input
////        System.out.println("");
//    }

//    public static void aiStrategize() {
//        // if whatever, do whatever
//    }
//
//    public static void calculateScore() {
//        // TODO
//    }
}
