package com.Lisa;

// Created by lisa on 2/26/15.

import java.util.LinkedList;
import java.util.Collections;

public class CardGroup {

    protected LinkedList<Card> group = new LinkedList<Card>();
    protected static String meldName = "";


    public void addCard(Card card) { this.group.add(card); }

    public void addCardAndSort(Card card) {
        // Add card to given group and sort group
        this.group.add(card);

        LinkedList<Card> unsortedCardGroup = new LinkedList<Card>();
        unsortedCardGroup.addAll(this.group);
        Collections.sort(unsortedCardGroup);
        // First sort in value order
        this.group.clear();

        for (int suit : Deck.suits) {
            // Then pull out cards in order, suit by suit
            for (Card unsortedCard : unsortedCardGroup) {
                if (unsortedCard.getSuit() == suit) {
                    this.group.add(unsortedCard);
                }
            }
        }
    }

    public void outputGroupOnOneLine() {
        // Outputs any given CardGroup in color on one line
        for (Card card : this.getGroup()) {
            card.outputCardToTerminalInColor();
            if (card != this.getGroup().getLast()) {
                System.out.print(", ");
            }
        }
        System.out.print("\n");
    }

    public boolean isValidRun() {
        // Determines if a given CardGroup is a valid run
        // This method cannot belong to the Run class because the
        // CardGroup cannot become a run until it passes this test
        if (this.getGroup().size() < 3) {
            // Smaller than 3 cards = not valid
            return false;
        }

        char suit = this.getGroup().getFirst().getSuit();
        int valueId = this.getGroup().getFirst().getValueId();

        for (Card card : this.getGroup()) {
            // All cards in group not the same suit = not valid
            if (card.getSuit() != suit) {
                return false;
            }

            // Cards do not proceed in value order = not valid
            if (card.getValueId() != valueId) {
                return false;
            }
            valueId ++;
        }
        return true;
    }

    public boolean isValidBook() {
        // Determines if a given CardGroup can become a valid book
        // This method cannot belong to the Book class because the
        // CardGroup cannot become a book until it passes this test
        if (this.getGroup().size() < 3) {
            // Smaller than 3 cards = not valid
            return false;
        }

        Card cardToCompare = this.getGroup().getFirst();

        for (int x = 1; x < (this.getGroup().size() - 1); x ++) {
            // Two cards the same suit OR two cards with different values = not valid
            Card otherCardInGroup = this.getGroup().get(x);
            if (cardToCompare.getSuit() == otherCardInGroup.getSuit() || cardToCompare.getValueId() != otherCardInGroup.getValueId()) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidLayOffFor(Card card) {
        // Subclass methods override this, should never be called
        System.out.println("Something went terribly wrong:");
        System.out.println("CardGroup method canAddToMeld called.");
        return false;
    }

    public void resetCanDiscardVariableForAll() {
        // Iterates through group and sets
        // boolean canDiscardThisTurn variable to true
        for (Card card : this.getGroup()) {
            card.canDiscard();
        }
    }

    public int getRoundScore() {
        // Determines points for round by adding up values
        // of cards in losing player's hand (face cards worth 10)
        int points = 0;
        for (Card card : this.group) {
            int cardValue = card.getValueId();
            if (cardValue > 10) {
                cardValue = 10;
            }
            points += card.getValueId();
        }
        return points;
    }

    // Getters
    public LinkedList<Card> getGroup() { return this.group; }

    public String getMeldName() {
        return this.meldName;
    }
}
