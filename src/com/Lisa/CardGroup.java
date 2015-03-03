package com.Lisa;

// Created by lisa on 2/26/15.

import java.util.LinkedList;
import java.util.Collections;

public class CardGroup {

    protected LinkedList<Card> group = new LinkedList<Card>();

    public void addCard(Card card) {
        this.group.add(card);
    }

    public void sortGroup() {
        // Adapted from http://www.programcreek.com/2013/01/sort-linkedlist-of-user-defined-objects-in-java/

        LinkedList<Card> unsortedCardGroup = new LinkedList<Card>();
        unsortedCardGroup.addAll(this.group);
        Collections.sort(unsortedCardGroup);
        this.group.clear();

        for (int suit : Deck.suits) {
            for (Card unsortedCard : unsortedCardGroup) {
                if (unsortedCard.getSuit() == suit) {
                    this.group.add(unsortedCard);
                }
            }
        }
    }

    // Getter
    public LinkedList<Card> getGroup() {
        return this.group;
    }
}
