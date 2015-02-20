package com.Lisa;
import java.util.*;>

// Created by lisa on 2/18/15.

public class Player {

    private LinkedList<Card> hand = new LinkedList<Card>();
    private LinkedList<ArrayList<Run>> runs = new LinkedList<ArrayList<Run>>();
    private LinkedList<ArrayList<Book>> books = new LinkedList<ArrayList<Book>>();

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void setHand(LinkedList<Card> hand) {
        this.hand = hand;
    }
}
