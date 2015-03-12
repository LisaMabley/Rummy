package com.Lisa;

import java.util.*;

// Created by lisa on 2/18/15.

public class Deck {

    private CardGroup stock = new CardGroup();
    private CardGroup discardPile = new CardGroup();
    protected LinkedList<CardGroup> melds = new LinkedList<CardGroup>();

    public static char[] suits = {9824, 9827, 9829, 9830};
    Random randomNumberGenerator;

    public Deck() {
        // Generate cards
        for (char x = 0; x < suits.length; x++) {
            int value;

            for (int y = 1; y < 14; y++) {
                value = y;
                Card newcard = new Card(suits[x], value);
                this.stock.addCard(newcard);
            }
        }
    }

    private Card getRandomCardFromDeck() {
        if (this.getStockPile().size() == 0) {
            this.getStockPile().addAll(getDiscardPileCards());
            getDiscardPileCards().clear();
        }

        randomNumberGenerator = new Random();
        int cardIndex = randomNumberGenerator.nextInt(this.getStockPile().size());
        Card randomCard = this.getStockPile().remove(cardIndex);
        return randomCard;
    }

    public void dealCards(int numCards, CardGroup group) {
        // Add given number of cards to given group
        // (usually a player's hand, sometimes the discard pile)

        Card randomCard;
        for (int x = 0; x < numCards; x++) {
            randomCard = getRandomCardFromDeck();
            group.addCardAndSort(randomCard);
        }
    }

    // Player actions for each round
    public void draw(Player player) {
        // Executes draw and outputs result
        player.outputHand();
        outputTopCardInDiscards();

        int drawChoice = player.makeDrawChoice(this);
        Card cardDrawn;
        String pileDrawnFrom = "";

        if (drawChoice == 1) {
            // Draw from stock pile
            cardDrawn = getRandomCardFromDeck();
            pileDrawnFrom = "the stock pile";

        } else {
            // Draw from discard pile
            cardDrawn = this.getDiscardPileCards().pop();
            cardDrawn.changeCanDiscardThisTurn();
            pileDrawnFrom = "the discard pile";
        }

        player.getHandGroup().addCardAndSort(cardDrawn);

        // Output result
        System.out.print("\n" + player.nickname + " drew ");
        cardDrawn.outputCardToTerminalInColor();
        System.out.println(" from " + pileDrawnFrom + ".");
    }

    public void meld(Player player) {
        // Executes meld and outputs result
        if (player.getHand().size() >= 3) {
            // Skip this step if player doesn't have enough cards to meld
            CardGroup meld = player.makeMeldChoice(this);

            // Output action
            if (meld.getGroup().isEmpty()) {
                System.out.println("\n" + player.nickname + " declined to meld.\n");

            } else {
                System.out.print("\n" + player.nickname + " melded ");
                meld.outputGroupOnOneLine();
            }

            if (player.handIsEmpty) {
                // Player has gone out
                System.out.println(player.nickname + " went out.");
            }
        }
    }

    public void layOff(Player player) {
        // Executes lay off
        player.makeLayOffChoice(this);

        if (player.handIsEmpty) {
            // Player has gone out
            System.out.println(player.nickname + " went out.");
        }
    }

    public void discard(Player player) {
        // Executes discard action
        Card cardToDiscard = player.makeDiscardChoice(this);

        // Output action
        System.out.print("\n" + player.nickname + " discarded ");
        cardToDiscard.outputCardToTerminalInColor();
        System.out.println("\n");

        if (player.handIsEmpty) {
            // Player has gone out
            System.out.println(player.nickname + " went out.");
        }
    }

    // Resetter
    public void resetDeckForNewRound() {
        this.getStockPile().addAll(discardPile.getGroup());
        this.stock.resetCanDiscardVariableForAll();
        this.discardPile.getGroup().clear();
        for (int x = 0; x < melds.size(); x++) {
            for (Card card : melds.get(x).getGroup()) {
                this.getStockPile().add(card);
            }
        }
        this.melds.clear();
    }

    // Outputters
    public void outputMelds() {
        System.out.println("All melds on the table:");
        for (int x = 0; x < melds.size(); x++) {
            System.out.print((x+1) + ". ");
            melds.get(x).outputGroupOnOneLine();
        }
    }

    public void outputTopCardInDiscards() {
        System.out.println("\nTop card in the discard pile: ");
        if (this.getDiscardPileCards().isEmpty()) {
            dealCards(1, discardPile);
        }

        discardPile.getGroup().peek().outputCardToTerminalInColor();
    }

    // Getters
    public LinkedList<Card> getStockPile() {
        return stock.getGroup();
    }
    public LinkedList<CardGroup> getMelds() {
        return melds;
    }

    public LinkedList<Card> getDiscardPileCards() {
        if (discardPile.getGroup().isEmpty()) {
            dealCards(1, discardPile);
        }

        return discardPile.getGroup();
    }
}
