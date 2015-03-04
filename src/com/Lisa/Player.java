package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public abstract class Player {

    protected CardGroup hand = new CardGroup();
    protected CardGroup runs = new CardGroup();
    protected CardGroup books = new CardGroup();
    protected String nickname = new String();

    public abstract Card makeDiscardChoice(Deck deck);
    public abstract int makeDrawChoice(Deck deck);
    public abstract void outputGameStatus(Deck deck);

    // Getters
    public LinkedList<Card> getHand() {
        return hand.getGroup();
    }

    public CardGroup getHandGroup() {
        return hand;
    }

    public LinkedList<Card> getRuns() {
        return runs.getGroup();
    }

    public LinkedList<Card> getBooks() {
        return books.getGroup();
    }
}
