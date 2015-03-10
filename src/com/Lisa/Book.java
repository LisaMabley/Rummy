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
    public boolean isValidLayOffWith(Card meldAddition) {
        // Determines if a given card is a valid addition to existing book
        if (this.getGroup().getFirst().isBookPartner(meldAddition) ||
                        this.getGroup().getLast().isBookPartner(meldAddition)) {
            return true;

        } else {
            return false;
        }
    }
}
