package com.Lisa;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

// Created by lisa on 3/3/15.

public class HumanPlayer extends Player {

    Scanner scanner;

    // Constructor
    public HumanPlayer(String name) {
        this.nickname = name;
    }

    @Override
    public Card makeDiscardChoice(Deck deck) {
        System.out.println("\nDISCARD:\nWhich card would you like to discard to end your turn?");
        Card cardToDiscard = this.selectCardFromPlayerHand();
        return cardToDiscard;
    }

    public int makeDrawChoice(Deck newDeck) {

        System.out.println("\nDRAW:");
        int drawChoice = 0;

        // Get valid response from user
        while (true) {
            System.out.println("Press 1 to draw from the stock pile, or 2 to draw from the discard pile.");

            try {
                scanner = new Scanner(System.in);
                drawChoice = scanner.nextInt();

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (drawChoice != 1 && drawChoice != 2) {
                // User input an invalid integer
                continue;
            }

            break;
        }
        return drawChoice;
    }

    public Card selectCardFromPlayerHand() {
        // Returns a positive int indicating which card user wishes to select from their hand

        int cardIndex = -1;

        // Get valid response from user
        while (true) {
            scanner = new Scanner(System.in);
            System.out.println("Please use your number keypad to enter which card, as counted from the left.");

            try {
                cardIndex = scanner.nextInt();
                cardIndex --;

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (cardIndex < 0 || cardIndex > this.getHand().size()) {
                // User input an invalid integer
                continue;
            }
            break;
        }
        Card selectedCard = this.getHand().get(cardIndex);

        return selectedCard;
    }

    public CardGroup makeMeldChoice(Deck newDeck) {
        System.out.println("\nMELD:\nWould you like to meld a run or book?\n" +
                "Please use your number keypad to enter which card, or 0 to pass.");

        CardGroup meldList = new CardGroup();

//        while (true) {
//            Card cardToMeld = this.selectCardFromPlayerHand();
//            meldList.add(cardToMeld);
//            System.out.println("Any more? Enter 0 if you're done");
//            Scanner scanner = new Scanner(System.in);
//            if (scanner.nextInt() == 0) {
//                cardToMeld.outputCardToTerminalInColor();
//                break;
//            }
//            scanner.close();
//            System.out.println("CURRENT MELD: ");
//            for (Card card : meldList) {
//                card.outputCardToTerminalInColor();
//                if (card != meldList.getLast()) {
//                    System.out.print(", ");
//                }
//            }
//        }
        return meldList;
    }

    public void outputGameStatus(Deck newDeck) {

        // Output human player cards
        System.out.println("\nYOUR HAND:");
        for (Card card : this.getHand()) {
            card.outputCardToTerminalInColor();
            ;
            if (card != this.getHand().getLast()) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nYOUR MELDS:");
        System.out.println("Runs");
        for (Card card : this.getRuns()) {
            card.outputCardToTerminalInColor();
        }
        System.out.println("Books");
        for (Card card : this.getBooks()) {
            card.outputCardToTerminalInColor();
        }

        // Display top card in discard pile
        System.out.println("\nDISCARD PILE:");

        newDeck.getDiscardPileCards().peek().outputCardToTerminalInColor();
        System.out.println("");
    }
}
