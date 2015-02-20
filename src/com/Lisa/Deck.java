package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public class Deck {

    private LinkedList<Card> activeDeck = new LinkedList<Card>();
    private LinkedList<Card> discardPile = new LinkedList<Card>();

    public Deck() {
        // Generate cards
        for (int x = 1; x < 5; x++) {
            int suit = x;
            int value = 0;

            for (int y = 1; y < 14; y++) {
                value = y;
                Card newcard = new Card(suit, value);
                this.activeDeck.add(newcard);
            }
        }
    }

    public LinkedList<Card> getActiveDeck() {
        return activeDeck;
    }

    public void setActiveDeck(LinkedList<Card> activeDeck) {
        this.activeDeck = activeDeck;
    }

    public LinkedList<Card> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(LinkedList<Card> discardPile) {
        this.discardPile = discardPile;
    }

    public Player dealCards(int numCards, Player player) {

        Random randomNumberGenerator = new Random();

        for (int x = 0; x < numCards; x ++) {
            int cardIndex = randomNumberGenerator.nextInt(this.getActiveDeck().size());
            player.getHand().add(this.getActiveDeck().get(cardIndex));
            this.getActiveDeck().remove(cardIndex);
        }

        return player;
    }
}
