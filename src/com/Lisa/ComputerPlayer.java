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
        for (Card card : this.getRuns()) {
            card.outputCardToTerminalInColor();
        }

        System.out.println("Books");
        for (Card card : this.getBooks()) {
            card.outputCardToTerminalInColor();
        }
    }
}
