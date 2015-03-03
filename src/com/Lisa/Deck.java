package com.Lisa;

import java.util.*;

// Created by lisa on 2/18/15.

public class Deck {

    private CardGroup stock = new CardGroup();
    private CardGroup discardPile = new CardGroup();

    public static int[] suits = { 9824, 9827, 9829, 9830 };

    public Deck() {
        // Generate cards
        for (int x = 1; x < 5; x++) {
            int suit = x;
            int value = 0;

            for (int y = 1; y < 14; y++) {
                value = y;
                Card newcard = new Card(suit, value);
                this.stock.addCard(newcard);
            }
        }
    }

    public LinkedList<Card> getStockPile() {

        return stock.getGroup();
    }

    public LinkedList<Card> getDiscardPile() {

        return discardPile.getGroup();
    }

    public void dealCards(int numCards, LinkedList<Card> group) {

        Random randomNumberGenerator = new Random();

        for (int x = 0; x < numCards; x ++) {
            int cardIndex = randomNumberGenerator.nextInt(this.getStockPile().size());
            group.add(this.getStockPile().remove(cardIndex));
        }
    }

    public void drawFromStockPile(LinkedList<Card> hand) {
        dealCards(1, hand);
    }

    public void drawFromDiscardPile(LinkedList<Card> hand) {
        hand.add(this.getDiscardPile().pop());
    }

    public void discardCard(Card card) {
        this.getDiscardPile().push(card);
    }
}
