package com.Lisa;

// Created by lisa on 2/20/15.

public class Run extends CardGroup {

    public Run(CardGroup initialMeld) {

        for (Card card : initialMeld.getGroup()) {
            this.group.add(card);
        }
    }
}
