package com.Lisa;

// Created by lisa on 2/18/15.

public class Card implements Comparable<Card> {

    private String name;
    private char suit;
    private int valueId;

    // If card was picked up from discard pile, this is false
    private boolean canDiscardThisTurn;

    // Constructor
    public Card(char suit, int value) {
        this.suit = suit;
        this.valueId = value;
        this.name = cardString(suit, value);
        this.canDiscardThisTurn = true;
    }

    // Generate card name variable when card objects are initialized
    private String cardString(int suitId, int valueId){

        String stringName = "";

        if (valueId < 11 && valueId > 1) {
            stringName = String.valueOf(valueId);

        } else if (valueId == 1) {
            stringName = "A";

        } else if (valueId == 11) {
            stringName = "J";

        } else if (valueId == 12) {
            stringName = "Q";

        } else if (valueId == 13) {
            stringName = "K";
        }

        stringName += Character.toString(this.suit);
        return stringName;
    }

    public int compareTo(Card card) {
        // Compare by valueID variable
        int comparedValue = card.valueId;
        if (this.valueId > comparedValue) {
            return 1;
        } else if (this.valueId == comparedValue) {
            return 0;
        } else {
            return -1;
        }
    }

    public void cantDiscardThisTurn() {
        // Change discard variable to false
        this.canDiscardThisTurn = false;
    }

    public void canDiscard() {
        // Change discard variable to false
        this.canDiscardThisTurn = true;
    }

    public void outputCardToTerminalInColor() {
        // Set colors and output to terminal
        // Hearts and diamonds in red, other suits black
        String ANSI_red = "\u001B[31m";
        String ANSI_reset_color = "\u001B[0m";

        if (this.getSuit() > 9828) {
            System.out.print(ANSI_red + this.getName() + ANSI_reset_color);
        } else {
            System.out.print(this.getName());
        }
    }

    public boolean isRunPartner(Card card) {
        // Compare card to another card and return true
        // if they have the same suit and have consecutive values

        int difference = Math.abs(this.getValueId() - card.getValueId());
        if (this.getSuit() == card.getSuit() && difference == 1) {
            return true;

        } else {
            return false;
        }
    }

    public boolean isBookPartner(Card card) {
        // Compare card to another card and return true
        // if they are different suits and have the same value

        if (this.getValueId() == card.getValueId() && this.getSuit() != card.getSuit()) {
            return true;

        } else {
            return false;
        }
    }

    public boolean isMeldPartner(Card card) {
        // Compare card to another card and return true
        // if they could meld in a book or run

        // Can card meld into a run?
        int difference = Math.abs(this.getValueId() - card.getValueId());
        if (this.getSuit() == card.getSuit() && difference == 1) {
            return true;
        }

        // Can card meld into a book?
        if (this.getValueId() == card.getValueId() && this.getSuit() != card.getSuit()) {
            return true;
        }

        return false;
    }

    public boolean canMeldWithAnyOtherCardInHand(CardGroup hand) {
        // Iterates through hand and returns true if this Card
        // object has meld possibilities within the hand

        for (Card card : hand.getGroup()) {
            if (this.isMeldPartner(card)) {
                return true;
            }
        }
        return false;
    }

    public int canLayoffToTableMeldAtIndex(Deck deck) {
        // Returns true if this Card object can lay off
        // to any meld on the table
        for (int x = 0; x < deck.getMelds().size(); x++) {
            if (deck.getMelds().get(x).isValidLayOffFor(this)) {
                return x;
            }
        }
        return -1;
    }

    // Getters
    public String getName() {
        return name;
    }
    public char getSuit() { return suit; }
    public int getValueId() { return valueId; }
    public boolean canDiscardThisTurn() {
        return canDiscardThisTurn;
    }
}
