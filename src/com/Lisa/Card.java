package com.Lisa;

// Created by lisa on 2/18/15.

public class Card implements Comparable<Card> {

    private String name;
    private char suit;
    private int suitId;
    private int valueId;

    private boolean canDiscardThisTurn;

    public Card(int suit, int value) {
        this.suitId = suit;
        this.valueId = value;
        this.name = cardString(suit, value);
        this.canDiscardThisTurn = true;
    }

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

        switch (suitId) {
            case 1:
                this.suit = 9829;
                break;
            case 2:
                this.suit = 9830;
                break;
            case 3:
                this.suit = 9824;
                break;
            case 4:
                this.suit = 9827;
                break;
        }

        stringName += Character.toString(this.suit);
        return stringName;
    }

    @Override
    public int compareTo(Card card) {
        int comparedValue = card.valueId;
        if (this.valueId > comparedValue) {
            return 1;
        } else if (this.valueId == comparedValue) {
            return 0;
        } else {
            return -1;
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public char getSuit() { return suit; }

    public int getValueId() { return valueId; }

    public boolean canDiscardThisTurn() {
        return canDiscardThisTurn;
    }

    public void changeCanDiscardThisTurn() {
        if (this.canDiscardThisTurn) {
            this.canDiscardThisTurn = false;
        } else {
            this.canDiscardThisTurn = true;
        }
    }
}
