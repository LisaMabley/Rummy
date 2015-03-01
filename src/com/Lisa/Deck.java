package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public class Deck {

    private CardGroup stock = new CardGroup();
    private CardGroup discardPile = new CardGroup();

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

    public LinkedList<Card> getStockCards() {

        return stock.getGroup();
    }

    public LinkedList<Card> getDiscardPileCards() {

        return discardPile.getGroup();
    }

    public CardGroup getDiscardPileGroup() {

        return discardPile;
    }

    public void dealCards(int numCards, CardGroup group) {

        Random randomNumberGenerator = new Random();

        for (int x = 0; x < numCards; x ++) {
            int cardIndex = randomNumberGenerator.nextInt(this.stock.getGroup().size());
            group.addCard(this.stock.getGroup().remove(cardIndex));
        }
    }

    public void drawFromStockPile(CardGroup hand) {
        dealCards(1, hand);
    }

    public void drawFromDiscardPile(CardGroup hand) {
        hand.getGroup().add(discardPile.getGroup().pop());
    }

//    public static void discard(Card card) {
//        //
//    }
}
