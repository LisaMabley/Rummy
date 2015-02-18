package com.Lisa;

// Created by lisa on 2/18/15.

public class Card {

    private String name;
    private int suitId;
    private int valueId;

    public Card(int suit, int value) {
        this.suitId = suit;
        this.valueId = value;
        this.name = cardString(suit, value);
    }

    private String cardString(int suitId, int valueId){

        String stringName = "";

        if (valueId < 11 && valueId > 1) {
            stringName = String.valueOf(valueId);

        } else if (valueId == 1) {
            stringName = "Ace";

        } else if (valueId == 11) {
            stringName = "Jack";

        } else if (valueId == 12) {
            stringName = "Queen";

        } else if (valueId == 13) {
            stringName = "King";
        }

        stringName += " of ";

        switch (suitId) {
            case 1:
                stringName += "Hearts";
                break;
            case 2:
                stringName += "Diamonds";
                break;
            case 3:
                stringName += "Spades";
                break;
            case 4:
                stringName += "Clubs";
                break;
        }

        return stringName;
    }

    public String getName() {
        return name;
    }

    public int getSuitId() {
        return suitId;
    }

    public int getValueId() {
        return valueId;
    }

}
