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

    @Override
    public Card makeDiscardChoice(Deck deck) {

        System.out.println("YOUR HAND:");
        this.hand.outputGroupOnOneLine();
        System.out.println("DISCARD:\nWhich card would you like to discard to end your turn?");
        Card cardToDiscard = this.selectCardFromPlayerHand();
        return cardToDiscard;
    }

    public int makeDrawChoice(Deck newDeck) {

        System.out.println("DRAW:");
        int drawChoice = 0;

        // Get valid response from user
        while (true) {
            System.out.println("Draw one card from the:\n1. Deck\n2. Discard pile");

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
        // TODO causes Index Out of Bounds error when 1 is selected
        while (true) {
            scanner = new Scanner(System.in);
            System.out.println("Please use your number keypad to enter which card, as counted from the left.");

            try {
                cardIndex = scanner.nextInt();
                cardIndex--;

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
        //TODO: Take Computer Player Code to Sort etc


        CardGroup emptyGroup = new CardGroup();
        CardGroup possibleMeld = new CardGroup();
        this.hand.outputGroupOnOneLine();
        System.out.println("MELD:\nWould you like to meld a run or book?\n" +
                "1. Meld cards\n2. Pass");

            while (true) {

                scanner = new Scanner(System.in);
                int humanChoice = scanner.nextInt();

                if (humanChoice == 1) {
                    System.out.println("\nYOUR HAND:");
                    this.hand.outputGroupOnOneLine();
                    Card cardToMeld = this.selectCardFromPlayerHand();
                    possibleMeld.addCardAndSort(cardToMeld);

                    System.out.println("CURRENT MELD: ");
                    possibleMeld.outputGroupOnOneLine();
                    System.out.println("\nAny more cards to meld?\n1. Add more\n2. Cancel\n3. Done");



                } else if (humanChoice == 2) {
//                    System.out.println("No melds created"); JESSE: Not necessary because meld method in Deck outputs this
                    return emptyGroup;
                } else if (humanChoice == 3) {
                    if (possibleMeld.isValidRun()) {
                        Run newRun = new Run(possibleMeld);
                        this.runs.add(newRun);
                        for (Card card : possibleMeld.getGroup()) {
                            this.hand.getGroup().remove(card);
                        }
                        return newRun;

                    } else if (possibleMeld.isValidBook()) {
                        Book newBook = new Book(possibleMeld);
                        this.books.add(newBook);
                        for (Card card : possibleMeld.getGroup()) {
                            this.hand.getGroup().remove(card);
                        }
                        return newBook;
                    } else {
                        System.out.println("That is not a valid meld.");
                        possibleMeld.getGroup().clear();
                        continue;
                    }
//                    System.out.println("Not a valid meld"); JESSE: Repeated from above
//                    return emptyGroup;

                } else {
                    // Consider: if they enter a higher number than 3, maybe they made a mistake
                    // and were trying to choose a card from their hand? Should we check?
                    return emptyGroup;
                }
            }
        }

    public void outputGameStatus(Deck newDeck) {

        // Output human player cards
        System.out.println("\nYOUR HAND:");
        this.hand.outputGroupOnOneLine();

        System.out.println("YOUR MELDS:");
        System.out.println("Runs");
        for (int x = 0; x < this.getRuns().size(); x++) {
            this.getRuns().get(x).outputGroupOnOneLine();
        }

        System.out.println("Books");
        for (int x = 0; x < this.getBooks().size(); x++) {
            this.getBooks().get(x).outputGroupOnOneLine();
        }

        // Display top card in discard pile
        System.out.println("\nDISCARD PILE:");

        newDeck.getDiscardPileCards().peek().outputCardToTerminalInColor();
        System.out.println("\n");
    }
}
