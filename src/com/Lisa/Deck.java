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

    public void dealCards(int numCards, CardGroup group) {
        // Add given number of cards to given group
        // (usually a player's hand, sometimes the discard pile)

        randomNumberGenerator = new Random();

        for (int x = 0; x < numCards; x++) {
            int cardIndex = randomNumberGenerator.nextInt(this.getStockPile().size());
            group.addCardAndSort(this.getStockPile().remove(cardIndex));
        }
    }

    public void draw(Player player) {
        // Executes draw and outputs result
        player.outputHand();
        outputTopCardInDiscards();

        int drawChoice = player.makeDrawChoice(this);
        Card cardDrawn;
        String pileDrawnFrom = "";

        if (drawChoice == 1) {
            // Draw from stock pile
            randomNumberGenerator = new Random();
            int cardIndex = randomNumberGenerator.nextInt(this.getStockPile().size());
            cardIndex --; // Prevent off-by-1 errors if last number is selected
            cardDrawn = this.getStockPile().remove(cardIndex);
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

            if (player.hand.groupIsEmpty()) {
                // Player has gone out
                System.out.println(player.nickname + " went out.");
            }
        }
    }

    public void layOff(Player player) {
        // Executes lay off
        player.makeLayOffChoice(this);

        if (player.hand.groupIsEmpty()) {
            // Player has gone out
            System.out.println(player.nickname + " went out.");
        }
    }

    public void discard(Player player) {
        // Executes discard action
        Card cardToDiscard;

        // Only allows cards to be discarded that have not
        // been drawn from the discard pile this turn
        while (true) {
            cardToDiscard = player.makeDiscardChoice(this);
            if (cardToDiscard.canDiscardThisTurn()) {
                break;
            } else {
                System.out.println("You cannot discard a card you just drew from the discard pile this turn.\nPlease choose a different card.");
            }
        }

        player.getHand().remove(cardToDiscard);
        this.getDiscardPileCards().push(cardToDiscard);

        // Output action
        System.out.print("\n" + player.nickname + " discarded ");
        cardToDiscard.outputCardToTerminalInColor();
        System.out.println("\n");

        if (player.hand.groupIsEmpty()) {
            // Player has gone out
            System.out.println(player.nickname + " went out.");
        }
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

    public LinkedList<Card> getDiscardPileCards() {
        if (discardPile.getGroup().isEmpty()) {
            dealCards(1, discardPile);
        }

        return discardPile.getGroup();
    }

    public LinkedList<CardGroup> getMelds() {
        return melds;
    }
}
