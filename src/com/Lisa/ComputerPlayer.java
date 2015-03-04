package com.Lisa;

// Created by lisa on 3/1/15.

import java.util.LinkedList;

public class ComputerPlayer extends Player {

    // Constructor
    public ComputerPlayer(String name) {
        this.nickname = name;
    }

    private boolean possibleMelds(Card card) {
        // Check for possible melds in computer player's hand with any given card

        boolean possibleMelds = false;

        for (Card cardInHand : hand.getGroup()) {
            int difference = Math.abs(cardInHand.getValueId() - card.getValueId());

            if (cardInHand.getSuit() == card.getSuit() && difference == 1) {
                // If the discard pile option increases chances of a Run
                possibleMelds = true;
            } else if (cardInHand.getValueId() == card.getValueId()) {
                // If the discard pile option increases chances of a Book
                possibleMelds = true;
            }
        }

        return possibleMelds;
    }

    @Override
    public Card makeDiscardChoice(Deck deck) {
        // Evaluates computer player's options and chooses which card to discard

        LinkedList<Card> discardPossibilities = new LinkedList<Card>();
        discardPossibilities.addAll(hand.getGroup());
        Card cardToDiscard = discardPossibilities.peek();
        boolean cardDrawnFromDiscardPileThisTurn = false;
        Card cardFromDiscard = hand.getGroup().getFirst();

        // Remove cards with possible melds from consideration
        for (Card card : hand.getGroup()) {
            if (!card.canDiscardThisTurn()) {
                cardDrawnFromDiscardPileThisTurn = true;
                cardFromDiscard = card;
                discardPossibilities.remove(card);
            } else if (possibleMelds(card)) {
                discardPossibilities.remove(card);
            }
        }

        if (discardPossibilities.isEmpty()) {
            discardPossibilities.addAll(hand.getGroup());
            if (cardDrawnFromDiscardPileThisTurn == true) {
                discardPossibilities.remove(cardFromDiscard);
            }
        }

        // Choose smallest card of smallest suit in hand from remaining cards
        int numCardsInSmallestSuit = hand.getGroup().size(); // Whole hand
        int smallestSuit = 0;
        int smallestValue = 14; // Start with highest card value

        for (int suit : Deck.suits) {
            int cardsOfThisSuitInHand = 0;
            for (Card card : hand.getGroup()) {
                if (card.getSuit() == suit) {
                    cardsOfThisSuitInHand ++;
                }
            }

            if (cardsOfThisSuitInHand < numCardsInSmallestSuit) {
                smallestSuit = suit;
            }

            for (Card card : discardPossibilities) {
                if ((card.getSuit() == smallestSuit) && (card.getValueId() < smallestValue)) {
                    // Card is smallest value of smallest suit in hand
                    cardToDiscard = card;
                }
            }
        }
        return cardToDiscard;
    }

    public int makeDrawChoice(Deck newDeck) {
        // Evaluates options and chooses which pile to draw from

        Card discardOption = newDeck.getDiscardPileCards().peek();
        boolean discardOptionProvidesPossibleMelds = possibleMelds(discardOption);

        // Return computer player's choice
        if (!discardOptionProvidesPossibleMelds) {
            // Draw from stock pile
            return 1;

        } else {
            // Draw from discard pile
            return 2;
        }
    }

    public CardGroup makeMeldChoice(Deck newDeck) {
        CardGroup emptyGroup = new CardGroup();
        Run newRun = new Run();

        // Look for runs
        for (Card cardToCompare : this.getHand()) {
            if (cardToCompare != this.getHand().getLast()) {
                int x = this.getHand().indexOf(cardToCompare);
                x ++;
                while ((this.getHand().get(x).getValueId() - this.getHand().get(x-1).getValueId() == 1) &&
                        (this.getHand().get(x).getSuit() == this.getHand().get(x-1).getSuit())) {
                    newRun.addCard(this.getHand().get(x));
                    x ++;
                }
            }

            if (newRun.getGroup().size() >= 2) {
                newRun.addCardAndSort(cardToCompare);
                this.runs.add(newRun);
                return newRun;

            } else {
                newRun.getGroup().clear();
            }
        }

        // Look for books
        int numCardsForBook = 0;
        Book newBook = new Book();

        for (Card cardToCompare : this.getHand()) {
            for (Card otherCardInHand : this.getHand()) {
                if ((cardToCompare.getValueId() == otherCardInHand.getValueId()) && (cardToCompare.getSuit() != otherCardInHand.getSuit())) {
                    newBook.addCard(otherCardInHand);
                    numCardsForBook ++;
                }
                if (numCardsForBook >= 2) {
                    // We have a book!
                    newBook.addCardAndSort(cardToCompare);
                    this.books.add(newBook);
                    return newBook;
                }
            }
            numCardsForBook = 0;
            newBook.getGroup().clear();
        }

        return emptyGroup;
    }

    public void outputGameStatus(Deck deck) {
        // Output computer player's cards
        // TODO REMOVE WHEN GAME IS FINAL
        System.out.println("\nYOUR OPPONENT'S HAND:");
        for (Card card : this.getHand()) {
            card.outputCardToTerminalInColor();
            ;
            if (card != this.getHand().getLast()) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nYOUR OPPONENT'S MELDS:");
        System.out.println("Runs");
        for (int x = 0; x < this.getRuns().size(); x++) {
            for (Card card : this.getRuns().get(x).getGroup()) {
                card.outputCardToTerminalInColor();
            }
            System.out.println("\n");
        }

        System.out.println("Books");
        for (int x = 0; x < this.getBooks().size(); x++) {
            for (Card card : this.getBooks().get(x).getGroup()) {
                card.outputCardToTerminalInColor();
            }
            System.out.println("\n");
        }
    }
}

