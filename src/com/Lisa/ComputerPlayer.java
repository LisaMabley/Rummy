package com.Lisa;

// Created by lisa on 3/1/15.

public class ComputerPlayer extends Player {

    public void aiDraw(Deck deck) {
        // Evaluates options and chooses which pile to draw from

        Card discardOption = deck.getDiscardPile().peek();
        boolean drawFromDiscard = false;

        // Check for possible melds with top card in discard pile
        for (Card card : hand.getGroup()) {
            int difference = Math.abs(discardOption.getValueId() - card.getValueId());

            if (discardOption.getSuit() == card.getSuit() && difference == 1) {
                // If the discard pile option increases chances of a Run
                drawFromDiscard = true;
            } else if (discardOption.getValueId() == card.getValueId()) {
                // If the discard pile option increases chances of a Book
                drawFromDiscard = true;
            }
        }

        // Execute AIs choice
        if (drawFromDiscard) {
            deck.drawFromDiscardPile(hand.getGroup());
            System.out.println("Your cunning opponent is drawing from the discard pile.");

        } else {
            deck.drawFromStockPile(hand.getGroup());
            System.out.println("Your cunning opponent is drawing from the stock pile.");
        }
    }
}
