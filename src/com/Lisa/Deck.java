package com.Lisa;

import java.util.*;

// Created by lisa on 2/18/15.

public class Deck {

    private CardGroup stock = new CardGroup();
    private CardGroup discardPile = new CardGroup();

    public static char[] suits = { 9824, 9827, 9829, 9830 };

    public Deck() {
        // Generate cards
        for (char x = 0; x < suits.length; x++) {
            int value = 0;

            for (int y = 1; y < 14; y++) {
                value = y;
                Card newcard = new Card(suits[x], value);
                this.stock.addCard(newcard);
            }
        }
    }

    public LinkedList<Card> getStockPile() {

        return stock.getGroup();
    }

    public CardGroup getDiscardPile() {

        if (discardPile.getGroup().isEmpty()) {
            dealCards(1, discardPile);
        }

        return discardPile;
    }

    public LinkedList<Card> getDiscardPileCards() {

        if (discardPile.getGroup().isEmpty()) {
            dealCards(1, discardPile);
        }

        return discardPile.getGroup();
    }

    public void dealCards(int numCards, CardGroup group) {

        Random randomNumberGenerator = new Random();

        for (int x = 0; x < numCards; x ++) {
            int cardIndex = randomNumberGenerator.nextInt(this.getStockPile().size());
            group.addCardAndSort(this.getStockPile().remove(cardIndex));
        }
    }

    public void drawFromStockPile(CardGroup hand) {
        dealCards(1, hand);
    }

    public void drawFromDiscardPile(CardGroup hand) {
        hand.getGroup().add(this.getDiscardPile().getGroup().pop());
    }

    public void discard(Player player) {

        Card cardToDiscard = player.makeDiscardChoice(this);
        player.getHand().remove(cardToDiscard);
        this.getDiscardPileCards().push(cardToDiscard);

        // Output action
        System.out.print("\n" + player.nickname + " discarded ");
        cardToDiscard.outputCardToTerminalInColor();
    }
}
