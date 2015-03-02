package com.Lisa;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        // Initialize hands
        Player humanPlayer = new Player();
        ComputerPlayer computerAiPlayer = new ComputerPlayer();

        // Opening deal
        newDeck.dealCards(10, humanPlayer.getHand());
        newDeck.dealCards(10, computerAiPlayer.getHand());
        newDeck.dealCards(1, newDeck.getDiscardPile());

        // While loop to come to repeat turns
        outputGameStatus(humanPlayer.getHand(), newDeck);
        draw(humanPlayer.getHand(), newDeck);
        discard(humanPlayer.getHand(), newDeck);

        // Computer player's turn
        computerAiPlayer.aiDraw(newDeck);
        outputGameStatus(computerAiPlayer.getHand(), newDeck);
        computerAiPlayer.aiDiscard(newDeck);
        outputGameStatus(computerAiPlayer.getHand(), newDeck);

        // While whatever startRound()
        // When whatever calculateScore()
        // Display score
        // Ask if player wants to play again
    }

//    public static void startRound() {
//        // interactWithHuman()
//        // aiStrategize()
//        // check if any player's hand is empty (they have won)
//    }

    public static void outputGameStatus(LinkedList<Card> hand, Deck newDeck) {

        // Output player cards
        System.out.println("\nYOUR HAND:");
        for (Card card : hand) {
            outputCardToTerminalInColor(card);
            if (card != hand.getLast()) {
                System.out.print(", ");
            }
        }

        // Display top card in discard pile
        System.out.println("\n\nDISCARD PILE:");
        if (newDeck.getDiscardPile().isEmpty()) {
            newDeck.dealCards(1, newDeck.getDiscardPile());
        }
        outputCardToTerminalInColor(newDeck.getDiscardPile().peek());

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

    public static void draw(LinkedList<Card> hand, Deck newDeck) {
        System.out.println("DRAW:");
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
                newDeck.drawFromStockPile(hand);
                break;
            case 2:
                newDeck.drawFromDiscardPile(hand);
                break;
        }
        outputGameStatus(hand, newDeck);
    }

    public static void discard(LinkedList<Card> hand, Deck newDeck) {
        System.out.println("DISCARD:\nWhich card would you like to discard to end your turn?");
        int indexOfCardToDiscard = selectCard(hand);

        // Output action
        System.out.print("\nDiscarded ");
        outputCardToTerminalInColor(hand.get(indexOfCardToDiscard));

        //  Discard selected card
        newDeck.discardCard(hand.remove(indexOfCardToDiscard));

        // For confirmation. DELETE when game is final
        outputGameStatus(hand, newDeck);
    }

    public static int selectCard(LinkedList<Card> hand) {
        // Returns a positive int indicating which card user wishes to select from their hand

        int cardIndex = -1;

        // Get valid response from user
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please use your number keypad to enter which card, as counted from the left.");

            try {
                cardIndex = scanner.nextInt();
                cardIndex --;

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (cardIndex < 0 || cardIndex > hand.size()) {
                // User input an invalid integer
                continue;
            }
            break;
        }
        return cardIndex;
    }
}

//    public static void calculateScore() {
//        // TODO
//    }
//}
