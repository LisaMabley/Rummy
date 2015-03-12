package com.Lisa;

// Created by lisa on 2/20/15.

public class Book extends CardGroup {

    public Book(CardGroup initialMeld) {
        // New book requires cards to be initialized
        for (Card card : initialMeld.getGroup()) {
            this.group.add(card);
        }
    }

    @Override
    public boolean isValidLayOffFor(Card card) {
        // Returns true if given card can add to this book object
        Card first = this.getGroup().getFirst();
        Card last = this.getGroup().getLast();
        if (card.isBookPartner(first) || card.isBookPartner(last)) {
            return true;

        } else {
            return false;
        }
    }
}
