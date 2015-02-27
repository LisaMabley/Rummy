package com.Lisa;

// Created by lisa on 2/18/15.

public class Card {

    private String name;
    private char suit;
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

    public String getName() {
        return name;
    }

    public char getSuit() { return suit; }

//    public int getSuitId() {
//        return suitId;
//    }

//    public int getValueId() {
//        return valueId;
//    }

}
