package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public abstract class Player {

    protected CardGroup hand = new CardGroup();
    protected String nickname = new String();

    public abstract int makeDrawChoice(Deck deck);
    public abstract CardGroup makeMeldChoice(Deck deck);
    public abstract void makeLayOffChoice(Deck deck);
    public abstract Card makeDiscardChoice(Deck deck);
    public abstract void outputHand();

    public void endTurn() {
        for (Card card : this.getHand()) {
            if (!card.canDiscardThisTurn()) {
                card.changeCanDiscardThisTurn();
            }
        }
    }

    // Getters
    public LinkedList<Card> getHand() {
        return hand.getGroup();
    }

    public CardGroup getHandGroup() {
        return hand;
    }
}
