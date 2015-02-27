package com.Lisa;

// Created by lisa on 2/26/15.

import java.util.LinkedList;

public class CardGroup {

    protected LinkedList<Card> group = new LinkedList<Card>();

    public LinkedList<Card> getGroup() {
        return this.group;
    }

    public void addCard(Card card) {
        this.group.add(card);
    }
}
