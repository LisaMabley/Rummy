package com.Lisa;

import java.util.InputMismatchException;
import java.util.Scanner;

// Created by lisa on 3/3/15.

public class HumanPlayer extends Player {

    Scanner scanner;

    // Constructor
    public HumanPlayer(String name) {
        this.nickname = name;
    }

    public int makeDrawChoice(Deck newDeck) {
        // Interacts with human player and returns their draw choice
        System.out.println("\n\nDRAW");
        return getValidInt(1, 2, "Draw one card from the:\n1. Deck\n2. Discard pile");
    }

    public CardGroup makeMeldChoice(Deck newDeck) {
        // Interacts with human player and returns their meld choice
        outputHand();
        System.out.println("\nMELD:\nWould you like to meld a run or book?\n");
        CardGroup emptyGroup = new CardGroup(); // Null object returned if no meld is created
        CardGroup possibleMeld = new CardGroup(); // Build meld in this list
        int humanChoice = getValidInt(1, 2, "1. Meld cards\n2. Pass");

        while (true) {

            if (humanChoice == 1) {
                int humanCardSelection = getValidInt(1, (this.getHand().size() + 1),
                        "Please use your number keypad to enter which card, as counted from the left.");
                Card cardToMeld = this.getHand().get((humanCardSelection - 1));
                possibleMeld.addCardAndSort(cardToMeld);

                System.out.println("CURRENT MELD: ");
                possibleMeld.outputGroupOnOneLine();
                System.out.println("\nAny more cards to meld?\n");
                humanChoice = getValidInt(1, 3, "1. Add more 2. Cancel 3. Done");

            } else if (humanChoice == 2) {
                return emptyGroup;

            } else if (humanChoice == 3) {
                if (possibleMeld.isValidRun()) {
                    Run newRun = new Run(possibleMeld);
                    newDeck.melds.add(newRun);
                    for (Card card : possibleMeld.getGroup()) {
                        this.hand.getGroup().remove(card);
                    }
                    this.isHandEmpty();
                    return newRun;

                } else if (possibleMeld.isValidBook()) {
                    Book newBook = new Book(possibleMeld);
                    newDeck.melds.add(newBook);
                    for (Card card : possibleMeld.getGroup()) {
                        this.hand.getGroup().remove(card);
                    }
                    this.isHandEmpty();
                    return newBook;

                } else {
                    System.out.println("That is not a valid meld.");
                    possibleMeld.getGroup().clear();
                }

            } else {
                return emptyGroup;
            }
        }
    }

    public void makeLayOffChoice(Deck deck) {
        // Interacts with human player and returns their lay off choice
        outputHand();
        System.out.println("\nLAY OFF CARDS:\nWould you like to lay off cards, adding to existing runs or books?\n");
        deck.outputMelds();
        int humanChoice = getValidInt(1, 2, "\n1. Lay off cards\n2. Pass");

        while (true) {

            if (humanChoice == 1) {
                System.out.println("\nYOUR HAND:");
                this.hand.outputGroupOnOneLine();
                System.out.println("Which meld would you like to add to?");
                int indexOfSelectedMeld = getValidInt(1, (deck.getMelds().size() + 1), "Use your number keypad to enter which meld.");
                indexOfSelectedMeld--;
                System.out.println("Which card would you like to add to this meld?");
                int indexOfCardToMeld = getValidInt(1, (this.getHand().size() + 1),
                        "Please use your number keypad to enter which card, as counted from the left.");
                indexOfCardToMeld--;
                Card cardToLayOff = this.getHand().get(indexOfCardToMeld);
                CardGroup meldToAddTo = deck.melds.get(indexOfSelectedMeld);

                if (meldToAddTo.isValidLayOffFor(cardToLayOff)) {
                    meldToAddTo.addCardAndSort(cardToLayOff);
                    this.getHand().remove(cardToLayOff);
                } else {
                    System.out.println("That is not a valid combination.");
                }

                meldToAddTo.outputGroupOnOneLine();
                humanChoice = getValidInt(1, 2, "\nAny more cards to lay off?\n1. Lay off more cards\n2. Done");

            } else {
                this.isHandEmpty();
                break;
            }
        }
    }

    public Card makeDiscardChoice(Deck deck) {
        // Interacts with human player and returns their discard choice
        System.out.println("\nDISCARD");
        outputHand();
        int humanCardSelection;
        Card cardToDiscard;

        while (true) {
            humanCardSelection = getValidInt(0, this.getHand().size() + 1, "Which card would you like to discard to end your turn?");
            cardToDiscard = this.getHand().get((humanCardSelection - 1));
            if (cardToDiscard.canDiscardThisTurn()) {
                this.getHand().remove(cardToDiscard);
                deck.getDiscardPileCards().push(cardToDiscard);
                this.isHandEmpty();
                return cardToDiscard;
            } else {
                // Only allows cards to be discarded that have not
                // been drawn from the discard pile this turn
                System.out.println("You cannot discard a card you just drew from the discard pile this turn.\nPlease choose a different card.");
            }
        }
    }

    public int setWinThreshold() {
        // Interacts with human player and returns their choice of win threshold
        int winThreshold = getValidInt(1, 10000, "How many points would you like to play up to?");
        return winThreshold;
    }

    public boolean playAgain() {
        // Returns true if human player indicates they'd like to play again
        System.out.println("Would you like to play again?\nPress Y for yes, and other key to stop.");
        if (scanner.next().equalsIgnoreCase("Y")) {
            return true;

        } else {
            return false;
        }
    }

    public int getValidInt(int minValue, int maxValue, String prompt) {
        // Displays prompt and returns valid positive int
        int playerChoice;

        while (true) {
            scanner = new Scanner(System.in);
            System.out.println(prompt);

            try {
                playerChoice = scanner.nextInt();

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (playerChoice < minValue || playerChoice > maxValue) {
                // User input an invalid integer
                continue;
            }
            break;
        }
        return playerChoice;
    }

    public void outputHand() {
        // Output human player cards
        System.out.println("\nYOUR HAND:");
        this.hand.outputGroupOnOneLine();
    }
}
