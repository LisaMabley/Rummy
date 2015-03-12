package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public abstract class Player {

    protected CardGroup hand = new CardGroup();
    protected String nickname = new String();
    protected int score = 0;
    protected boolean handIsEmpty = false;

    public abstract int makeDrawChoice(Deck deck);
    public abstract CardGroup makeMeldChoice(Deck deck);
    public abstract void makeLayOffChoice(Deck deck);
    public abstract Card makeDiscardChoice(Deck deck);
    public abstract void outputHand();

    public void endTurn() {
        // Resets any card picked up from discard pile this turn
        this.getHandGroup().resetCanDiscardVariableForAll();
    }

    protected void isHandEmpty() {
        // Returns true if player's hand is empty
        if (this.getHand().size() == 0) {
            this.handIsEmpty = true;
        }
    }

    public int roundLost() {
        // Returns point value for all cards remaining in losing player's hand
        return this.hand.getRoundScore();
    }

    public void roundWon(int roundPoints) {
        // Outputs winning player and transfers points to them
        System.out.println(this.nickname + " won this round, for " + roundPoints + " points.");
        this.score += roundPoints;
        System.out.println(this.nickname + ": " + this.score);
    }

    public void resetHandForNewRound() {
        // Clear hand and reset emptyhand flag
        this.getHand().clear();
        this.handIsEmpty = false;
    }

    // Getters
    public LinkedList<Card> getHand() { return hand.getGroup(); }
    public CardGroup getHandGroup() {
        return hand;
    }
    public String getNickname() { return nickname; }
    public int getScore() { return score; }

    // Setter
    public void setScore(int score) {
        this.score = score;
    }
}
