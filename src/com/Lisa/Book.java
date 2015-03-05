package com.Lisa;

// Created by lisa on 2/20/15.

public class Book extends CardGroup {
    private int valueId;

    public Book(CardGroup initialMeld) {

        this.valueId = initialMeld.getGroup().getFirst().getValueId();

        for (Card card : initialMeld.getGroup()) {
            this.group.add(card);
        }
    }
}
