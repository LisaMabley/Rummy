package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public abstract class Player {

    protected CardGroup hand = new CardGroup();
    protected String nickname = new String();

    public abstract Card makeDiscardChoice(Deck deck);
    public abstract int makeDrawChoice(Deck deck);
    public abstract CardGroup makeMeldChoice(Deck deck);
    public abstract void outputGameStatus(Deck deck);

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
