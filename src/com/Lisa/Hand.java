package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public class Hand {

    private LinkedList<Card> hand = new LinkedList<Card>();
//    private LinkedList<ArrayList<Card>> runs = new LinkedList<ArrayList<Card>();
//    private LinkedList<ArrayList<Card> books = new LinkedList<ArrayList<Card>();

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void setHand(LinkedList<Card> hand) {
        this.hand = hand;
    }
}
