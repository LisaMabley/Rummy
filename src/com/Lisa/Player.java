package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public class Player {

    private CardGroup hand = new CardGroup();
    private LinkedList<Run> runs = new LinkedList<Run>();
    private LinkedList<Book> books = new LinkedList<Book>();

    public LinkedList<Card> getHand() {
        return hand.getGroup();
    }
    // This is a change
}
