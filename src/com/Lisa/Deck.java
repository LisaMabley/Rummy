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

    public CardGroup getDiscardPile() {

        return discardPile;
    }

    public LinkedList<Card> getDiscardPileCards() {

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

    public void discardCard(Player player, Card card) {
        player.getHand().remove(card);
        this.getDiscardPileCards().push(card);
    }
}
