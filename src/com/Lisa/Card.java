package com.Lisa;

// Created by lisa on 2/18/15.

import java.util.LinkedList;

public class Card implements Comparable<Card> {

    private String name;
    private char suit;
    private int valueId;

    private LinkedList<Card> runPartners = new LinkedList<Card>();
    private LinkedList<Card> bookPartners = new LinkedList<Card>();
    private LinkedList<Card> allMeldPartners = new LinkedList<Card>();

    private boolean canDiscardThisTurn;

    public Card(char suit, int value) {
        this.suit = suit;
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

    public void changeCanDiscardThisTurn() {
        if (this.canDiscardThisTurn) {
            this.canDiscardThisTurn = false;
        } else {
            this.canDiscardThisTurn = true;
        }
    }

    public void outputCardToTerminalInColor() {

        // Set colors for terminal output
        String ANSI_red = "\u001B[31m";
        String ANSI_reset_color = "\u001B[0m";

        if (this.getSuit() > 9828) {
            System.out.print(ANSI_red + this.getName() + ANSI_reset_color);
        } else {
            System.out.print(this.getName());
        }
    }

    public LinkedList<Card> getRunPartners() {
        return runPartners;
    }

    public LinkedList<Card> getBookPartners() {
        return bookPartners;
    }

    public LinkedList<Card> getAllMeldPartners() {
        return allMeldPartners;
    }

    public boolean isRunPartner(Card card) {
        if (this.runPartners.contains(card)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBookPartner(Card card) {
        if (this.bookPartners.contains(card)) {
            return true;
        } else {
            return false;
        }
    }

    public void addRunPartner(Card card) {
        this.runPartners.add(card);
    }

    public void addBookPartner(Card card) {
        this.bookPartners.add(card);
    }

    public void addAllMeldsPartner(Card card) {
        this.allMeldPartners.add(card);
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
}
