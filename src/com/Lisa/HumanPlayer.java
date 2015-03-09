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
        System.out.println("\n\nDRAW");
        return getValidInt(1, 2, "Draw one card from the:\n1. Deck\n2. Discard pile");
    }

    public CardGroup makeMeldChoice(Deck newDeck) {

        outputHand();
        System.out.println("\nMELD:\nWould you like to meld a run or book?\n");
        CardGroup emptyGroup = new CardGroup();
        CardGroup possibleMeld = new CardGroup();
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
                    return newRun;

                } else if (possibleMeld.isValidBook()) {
                    Book newBook = new Book(possibleMeld);
                    newDeck.melds.add(newBook);
                    for (Card card : possibleMeld.getGroup()) {
                        this.hand.getGroup().remove(card);
                    }
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
        outputHand();
        System.out.println("");
        System.out.println("LAY OFF CARDS:\nWould you like to lay off cards, adding to existing runs or books?\n");
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

                if (deck.melds.get(indexOfSelectedMeld).isValidLayOffWith(this.getHand().get(indexOfCardToMeld))) {
                    deck.melds.get(indexOfSelectedMeld).addCardAndSort(this.getHand().get(indexOfCardToMeld));
                    this.getHand().remove(indexOfCardToMeld);
                } else {
                    System.out.println("That is not a valid combination.");
                }

                humanChoice = getValidInt(1, 2, "\nAny more cards to lay off?\n1. Lay off more cards\n2. Done");

            } else {
                deck.outputMelds();
                break;
            }
        }
    }

    public Card makeDiscardChoice(Deck deck) {

        System.out.println("\nDISCARD");
        outputHand();
        int humanCardSelection = getValidInt(1, this.getHand().size(), "Which card would you like to discard to end your turn?");
        Card cardToDiscard = this.getHand().get((humanCardSelection - 1));
        return cardToDiscard;
    }

    public int getValidInt(int minValue, int maxValue, String prompt) {
        // Displays prompt and returns valid int

        int playerChoice;

        // Get valid response from user
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
