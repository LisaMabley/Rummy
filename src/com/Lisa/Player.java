package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public class Player {

    protected CardGroup hand = new CardGroup();
    protected LinkedList<Run> runs = new LinkedList<Run>();
    protected LinkedList<Book> books = new LinkedList<Book>();

    public LinkedList<Card> getHand() {
        return hand.getGroup();
    }

    public LinkedList<Card> getSortedHand() {
        hand.sortGroup();
        return hand.getGroup();
    }
}
