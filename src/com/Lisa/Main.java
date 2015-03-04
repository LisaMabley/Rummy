package com.Lisa;

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
        newDeck.dealCards(10, humanPlayer.getHandGroup());
        newDeck.dealCards(10, computerAiPlayer.getHandGroup());
        newDeck.dealCards(1, newDeck.getDiscardPile());
        
        outputGameStatus(humanPlayer.getHandGroup(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
        draw(humanPlayer.getHandGroup(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
        meld(humanPlayer.getHandGroup(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
        discard(humanPlayer.getHandGroup(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());

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

    public static void outputGameStatus(CardGroup handGroup, Deck newDeck,
                                        LinkedList<Card> books, LinkedList<Card> runs) {

        LinkedList<Card> handCards = handGroup.getGroup();

        // Output player cards
        System.out.println("\nYOUR HAND:");
        for (Card card : handCards) {
            card.outputCardToTerminalInColor();;
            if (card != handCards.getLast()) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nMELD PILE:");
        for (Card card : runs) {
            card.outputCardToTerminalInColor();;
        }

        // Display top card in discard pile
        System.out.println("\n\nDISCARD PILE:");
        if (newDeck.getDiscardPile().getGroup().isEmpty()) {
            newDeck.dealCards(1, newDeck.getDiscardPile());
        }
        newDeck.getDiscardPile().getGroup().peek().outputCardToTerminalInColor();

        // Display all runs and books on table
        System.out.println("\n");
    }

    public static void draw(CardGroup hand, Deck newDeck, LinkedList<Card> books, LinkedList<Card> runs) {
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
        outputGameStatus(hand, newDeck, books, runs);
    }

    public static void meld(CardGroup hand, Deck newDeck, LinkedList<Card> books, LinkedList<Card> runs) {
        System.out.println("MELD:\nWould you like to meld any runs or books?");
        LinkedList<Card> handCards = hand.getGroup();

        while (true) {
            LinkedList<Card> meldList = new LinkedList<Card>();
            outputGameStatus(hand, newDeck, books, runs);
            int indexOfCardToMeld = selectCard(handCards);
            meldList.add(handCards.get(indexOfCardToMeld));
//            int indexOfCardToMeld = selectCard(hand);
            System.out.println("Any more? Enter 0 if you're done");
//            meldList.add(hand.get(indexOfCardToMeld));
            if (indexOfCardToMeld == 0) {
                handCards.get(indexOfCardToMeld).outputCardToTerminalInColor();
                break;
            }
        }

        // Output action
        System.out.print("\nMelded \n");
        outputGameStatus(hand, newDeck, books, runs);

        // For confirmation. DELETE when game is final
//        outputGameStatus(hand, newDeck);
    }

    public static void discard(CardGroup hand, Deck newDeck, LinkedList<Card> books, LinkedList<Card> runs) {
        System.out.println("DISCARD:\nWhich card would you like to discard to end your turn?");
        int indexOfCardToDiscard = selectCard(hand.getGroup());

        // Output action
        System.out.print("\nDiscarded ");
        hand.getGroup().get(indexOfCardToDiscard).outputCardToTerminalInColor();

        //  Discard selected card
        newDeck.discardCard(hand.getGroup().remove(indexOfCardToDiscard));

        // For confirmation. DELETE when game is final
        outputGameStatus(hand, newDeck, books, runs);
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
