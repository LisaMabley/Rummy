package com.Lisa;

// Created by lisa on 2/20/15.

public class Run extends CardGroup {

    public static String meldName = "Run";

    public Run(CardGroup initialMeld) {

        for (Card card : initialMeld.getGroup()) {
            this.group.add(card);
        }
    }

    // Getter
    public String getMeldName() {
        return meldName;
    }

    @Override
    public boolean isValidLayOffFor(Card card) {
        // Returns true if given card can be added to this Run object
        Card first = this.getGroup().getFirst();
        Card last = this.getGroup().getLast();
        if (card.isRunPartner(first) || card.isRunPartner(last)) {
            return true;

        } else {
            return false;
        }
    }
}
