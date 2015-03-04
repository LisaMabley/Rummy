package com.Lisa;

// Created by lisa on 3/1/15.

import java.util.LinkedList;

public class ComputerPlayer extends Player {

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

    public void aiDraw(Deck deck) {
        // Evaluates options and chooses which pile to draw from

        Card discardOption = deck.getDiscardPile().getGroup().peek();
        boolean discardOptionProvidesPossibleMelds = possibleMelds(discardOption);

        // Execute AIs choice
        if (discardOptionProvidesPossibleMelds) {
            deck.drawFromDiscardPile(hand);
            System.out.println("Your cunning opponent is drawing from the discard pile.");

        } else {
            deck.drawFromStockPile(hand);
            System.out.println("Your cunning opponent is drawing from the stock pile.");
        }
    }

    public void aiDiscard(Deck deck) {
        // Evaluates options and chooses which card to discard

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

        System.out.print("Your cunning opponent has discarded ");
        cardToDiscard.outputCardToTerminalInColor();
        hand.getGroup().remove(cardToDiscard);
        deck.discardCard(this, cardToDiscard);
    }
}
