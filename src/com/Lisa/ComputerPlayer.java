package com.Lisa;

// Created by lisa on 3/1/15.

import java.util.LinkedList;

public class ComputerPlayer extends Player {

    // Constructor
    public ComputerPlayer(String name) {
        this.nickname = name;
    }

    @Override
    public Card makeDiscardChoice(Deck deck) {
        // Evaluates computer player's options and chooses which card to discard

        Card cardToDiscard = this.getHand().getFirst();
        boolean allCardsHavePossibleMelds = false;
        LinkedList<Card> discardPossibilities = new LinkedList<Card>();

        while (true) {
            // Add all cards from hand to list of possible discards
            discardPossibilities.addAll(this.getHand());

            for (Card cardToCompare : this.getHand()) {
                // Remove any card that has been picked up from discard pile this turn
                if (!cardToCompare.canDiscardThisTurn()) {
                    discardPossibilities.remove(cardToCompare);
                }

                if (!allCardsHavePossibleMelds) {
                    for (Card otherCardInHand : this.getHand()) {
                        // Remove cards with run or book possibilities within player's hand
                        if (cardToCompare.isRunPartner(otherCardInHand) || cardToCompare.isBookPartner(otherCardInHand)) {
                            discardPossibilities.remove(cardToCompare);
                        }
                    }
                }
            }

            if (discardPossibilities.isEmpty()) {
                allCardsHavePossibleMelds = true;

            } else {
                break;
            }
        }

        // Choose smallest card of smallest suit in hand from remaining cards
        int numCardsInSmallestSuit = hand.getGroup().size(); // Whole hand
        int smallestSuit = 0;
        int smallestValue = 14; // Start with highest card value

        // TODO if discard possibilities contains all suits, take card from smallest suit
        // This block find smallest suit in hand, variable is unused
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
                if ((card.getValueId() < smallestValue)) {
                    // Card is smallest value
                    cardToDiscard = card;
                }
            }
        }
        return cardToDiscard;
    }

    public int makeDrawChoice(Deck newDeck) {
        // Evaluates options and chooses which pile to draw from

        Card discardOption = newDeck.getDiscardPileCards().peek();

        for (Card card : this.getHand()) {
            if (discardOption.isRunPartner(card) || discardOption.isBookPartner(card)) {
                // If card from discard pile provides possible run or book options
                // Draw from discard pile
                return 2;
            }
        }

        // If not, draw from stock pile
        return 1;
    }

    public CardGroup makeMeldChoice(Deck newDeck) {
        // Makes computer player's meld choice
        CardGroup emptyGroup = new CardGroup();
        CardGroup possibleMeld = new CardGroup();

        // Look for runs
        for (Card cardToCompare : this.getHand()) {
            if (cardToCompare != this.getHand().getLast()) {
                int x = this.getHand().indexOf(cardToCompare);
                x ++;
                while ((this.getHand().get(x).getValueId() - this.getHand().get(x-1).getValueId() == 1) &&
                        (this.getHand().get(x).getSuit() == this.getHand().get(x-1).getSuit())) {
                    possibleMeld.addCard(this.getHand().get(x));
                    x ++;
                }
            }

            if (possibleMeld.getGroup().size() >= 2) {
                // We have a run!
                possibleMeld.addCardAndSort(cardToCompare);
                for (Card card : possibleMeld.getGroup()) {
                    this.hand.getGroup().remove(card);
                }

                Run newRun = new Run(possibleMeld);
                this.runs.add(newRun);
                return newRun;

            } else {
                possibleMeld.getGroup().clear();
            }
        }

        // Look for books
        int numCardsForBook = 0;

        for (Card cardToCompare : this.getHand()) {
            for (Card otherCardInHand : this.getHand()) {
                if ((cardToCompare.getValueId() == otherCardInHand.getValueId()) && (cardToCompare.getSuit() != otherCardInHand.getSuit())) {
                    possibleMeld.addCard(otherCardInHand);
                    numCardsForBook ++;
                }
                if (numCardsForBook >= 2) {
                    // We have a book!
                    possibleMeld.addCardAndSort(cardToCompare);
                    for (Card card : possibleMeld.getGroup()) {
                        this.hand.getGroup().remove(card);
                    }
                    Book newBook = new Book(possibleMeld);
                    this.books.add(newBook);
                    return newBook;
                }
            }
            numCardsForBook = 0;
            possibleMeld.getGroup().clear();
        }
        return emptyGroup;
    }

    public void outputGameStatus(Deck deck) {
        // Output computer player's cards
        // TODO REMOVE WHEN GAME IS FINAL
        System.out.println("YOUR OPPONENT'S HAND:");
        for (Card card : this.getHand()) {
            card.outputCardToTerminalInColor();
            if (card != this.getHand().getLast()) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nYOUR OPPONENT'S MELDS:");
        System.out.println("Runs");
        for (int x = 0; x < this.getRuns().size(); x++) {
            this.getRuns().get(x).outputGroupOnOneLine();
            for (Card card : this.getRuns().get(x).getGroup()) {
                card.outputCardToTerminalInColor();
            }
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

