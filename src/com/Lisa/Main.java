package com.Lisa;

import sun.awt.image.ImageWatched;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        Book humanBook = new Book();
        Book computerBook = new Book();

        Run humanRun = new Run();
        Run computerRun = new Run();

        // Initialize hands
        Player humanPlayer = new Player();
        Player computerAiPlayer = new Player();

        // Opening deal
        newDeck.dealCards(10, humanPlayer.getHand());
        newDeck.dealCards(10, computerAiPlayer.getHand());
        newDeck.dealCards(1, newDeck.getDiscardPile());

        outputGameStatus(computerAiPlayer.getHand(), humanPlayer.getHand(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
        draw(computerAiPlayer.getHand(), humanPlayer.getHand(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
        meld(computerAiPlayer.getHand(), humanPlayer.getHand(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
        discard(computerAiPlayer.getHand(), humanPlayer.getHand(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());

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

    public static void outputGameStatus(LinkedList<Card> cpuHand, LinkedList<Card> hand, Deck newDeck, LinkedList<Card> books, LinkedList<Card> runs) {
        // Output computer cards for test
        System.out.println("\nCPU HAND:");
        for (Card card : cpuHand) {
            outputCardToTerminalInColor(card);
            if (card != cpuHand.getLast()) {
                System.out.print(", ");
            }
        }

        // Output player cards
        System.out.println("\nYOUR HAND:");
        for (Card card : hand) {
            outputCardToTerminalInColor(card);
            if (card != hand.getLast()) {
                System.out.print(", ");
            }
        }
        System.out.println("\n\nMELD PILE:");
        for (Card card : runs) {
            outputCardToTerminalInColor(card);
        }
        for (Card card : books) {
            outputCardToTerminalInColor(card);
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

    public static void draw(LinkedList<Card> cpuHand, LinkedList<Card> hand, Deck newDeck, LinkedList<Card> books, LinkedList<Card> runs) {
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
        outputGameStatus(cpuHand, hand, newDeck, books, runs);
    }

    public static void meld(LinkedList<Card> cpuHand, LinkedList<Card> hand, Deck newDeck, LinkedList<Card> books, LinkedList<Card> runs) {
        System.out.println("MELD:\nWould you like to meld any runs or books?");
        LinkedList<Card> meldList = new LinkedList<Card>();
        while (true) {
            outputGameStatus(cpuHand, hand, newDeck, books, runs);
            int indexOfCardToMeld = selectCard(hand);
            meldList.add(hand.get(indexOfCardToMeld));
//            int indexOfCardToMeld = selectCard(hand);
            System.out.println("Any more? Enter 0 if you're done");
//            meldList.add(hand.get(indexOfCardToMeld));
            if (indexOfCardToMeld == 0) {
                outputCardToTerminalInColor(hand.get(indexOfCardToMeld));
                break;
            }
            System.out.println("CURRENT MELD: " );
            for (Card card : meldList) {
                outputCardToTerminalInColor(card);
                if (card != meldList.getLast()) {
                    System.out.print(", ");
                }
            }
        }


        // Output action
        System.out.print("\nMelded \n");
        outputGameStatus(cpuHand, hand, newDeck, books, runs);

        // For confirmation. DELETE when game is final
//        outputGameStatus(hand, newDeck);
    }

    public static void discard(LinkedList<Card> cpuHand, LinkedList<Card> hand, Deck newDeck, LinkedList<Card> books, LinkedList<Card> runs) {
        System.out.println("DISCARD:\nWhich card would you like to discard to end your turn?");
        int indexOfCardToDiscard = selectCard(hand);

        // Output action
        System.out.print("\nDiscarded ");
        outputCardToTerminalInColor(hand.get(indexOfCardToDiscard));

        //  Discard selected card
        newDeck.discardCard(hand.remove(indexOfCardToDiscard));

        // For confirmation. DELETE when game is final
        outputGameStatus(cpuHand, hand, newDeck, books, runs);
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
