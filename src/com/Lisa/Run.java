package com.Lisa;

// Created by lisa on 2/20/15.

public class Run extends CardGroup {

    private char suit;

    public Run(CardGroup initialMeld) {

        this.suit = initialMeld.getGroup().getFirst().getSuit();

        for (Card card : initialMeld.getGroup()) {
            this.group.add(card);
        }
    }
}
