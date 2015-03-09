package com.Lisa;

// Created by lisa on 2/20/15.

public class Book extends CardGroup {

    public Book(CardGroup initialMeld) {
        // New book requires cards to be initialized

        for (Card card : initialMeld.getGroup()) {
            this.group.add(card);
        }
    }
}
