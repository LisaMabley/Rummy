package com.Lisa;

// Created by lisa on 3/1/15.

import java.util.Collections;
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

        // TODO fine tune discard choice
        // What if there are only 2 cards in hand?
        // This block finds smallest suit in hand, variable is unused
        // if discard possibilities contains all suits, take card from smallest suit
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
        // TODO test thoroughly to be sure index out of bounds exceptions are really fixed

        CardGroup emptyGroup = new CardGroup();
        CardGroup possibleMeld = new CardGroup();

        // Look for runs
        // Would be nice to make sure this picked longest run, if more than one in hand
        // This just picks the first one it comes across
        int handSize = this.getHand().size();

        for (int x = 0; x < handSize - 2; x ++) {
            Card cardToCompare = this.getHand().get(x);
            Card nextCardInHand = this.getHand().get(x + 1);

            while (cardToCompare.isRunPartner(nextCardInHand)) {
                possibleMeld.addCard(cardToCompare);
                x ++;
                cardToCompare = this.getHand().get(x);

                if (cardToCompare == this.getHand().getLast()) {
                    break;

                } else {
                    nextCardInHand = this.getHand().get(x + 1);
                }
            }

            if (possibleMeld.getGroup().size() >= 2) {
                // We have a run!
                possibleMeld.addCardAndSort(cardToCompare);

                if (possibleMeld.isValidRun()) {
                    for (Card card : possibleMeld.getGroup()) {
                        this.hand.getGroup().remove(card);
                    }

                    Run newRun = new Run(possibleMeld);
                    this.runs.add(newRun);
                    return newRun;
                }

            } else {
                possibleMeld.getGroup().clear();
            }
        }

        // Look for books
        Collections.sort(this.getHand());

        for (int x = 0; x < handSize - 2; x ++) {
            Card cardToCompare = this.getHand().get(x);
            Card nextCardInHand = this.getHand().get(x + 1);

            while (cardToCompare.isBookPartner(nextCardInHand)) {
                possibleMeld.addCard(cardToCompare);
                x++;
                cardToCompare = this.getHand().get(x);

                if (cardToCompare == this.getHand().getLast()) {
                    break;

                } else {
                    nextCardInHand = this.getHand().get(x + 1);
                }
            }

            if (possibleMeld.getGroup().size() >= 2) {
                // We have a book!
                possibleMeld.addCardAndSort(cardToCompare);
                if (possibleMeld.isValidBook()) {
                    for (Card card : possibleMeld.getGroup()) {
                        this.hand.getGroup().remove(card);
                    }

                    Book newBook = new Book(possibleMeld);
                    this.books.add(newBook);
                    return newBook;
                }

            } else {
                possibleMeld.getGroup().clear();
            }
        }

        return emptyGroup;
    }

    public void outputGameStatus(Deck deck) {
        // Output computer player's cards
        // TODO remove when game is final
        System.out.println("YOUR OPPONENT'S HAND:");
        this.hand.outputGroupOnOneLine();

        System.out.println("\n\nYOUR OPPONENT'S MELDS:");
        System.out.println("Runs");
        for (int x = 0; x < this.getRuns().size(); x++) {
            this.getRuns().get(x).outputGroupOnOneLine();
        }

        // TODO found a bug where this prints Runs here and above
        System.out.println("Books");
        for (int x = 0; x < this.getBooks().size(); x++) {
            this.getBooks().get(x).outputGroupOnOneLine();
        }
    }
}

