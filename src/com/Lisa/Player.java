package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public class Player {

    protected CardGroup hand = new CardGroup();
    private CardGroup runs = new CardGroup();
    private CardGroup books = new CardGroup();

    public LinkedList<Card> getHand() {
        return hand.getGroup();
    }

    public LinkedList<Card> getRuns() {
        return runs.getGroup();
    }

    public LinkedList<Card> getBooks() {
        return books.getGroup();
    }
    // This is a change
}
