package com.Lisa;

import java.util.*;

// Created by lisa on 2/18/15.

public class Deck {

    private CardGroup stock = new CardGroup();
    private CardGroup discardPile = new CardGroup();

    public static char[] suits = {9824, 9827, 9829, 9830};
    Random randomNumberGenerator;

    public Deck() {
        // Generate cards
        for (char x = 0; x < suits.length; x++) {
            int value = 0;

            for (int y = 1; y < 14; y++) {
                value = y;
                Card newcard = new Card(suits[x], value);
                this.stock.addCard(newcard);
            }
        }
    }

    public LinkedList<Card> getStockPile() {

        return stock.getGroup();
    }

    public LinkedList<Card> getDiscardPileCards() {

        if (discardPile.getGroup().isEmpty()) {
            dealCards(1, discardPile);
        }

        return discardPile.getGroup();
    }

    public void dealCards(int numCards, CardGroup group) {

        randomNumberGenerator = new Random();

        for (int x = 0; x < numCards; x++) {
            int cardIndex = randomNumberGenerator.nextInt(this.getStockPile().size());
            group.addCardAndSort(this.getStockPile().remove(cardIndex));
        }
    }

    public void discard(Player player) {
        Card cardToDiscard;

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
    }

    public void draw(Player player) {

        int drawChoice = player.makeDrawChoice(this);
        Card cardDrawn;
        String pileDrawnFrom = "";

        if (drawChoice == 1) {
            // Draw from stock pile
            randomNumberGenerator = new Random();
            int cardIndex = randomNumberGenerator.nextInt(this.getStockPile().size());
            cardDrawn = this.getStockPile().remove(cardIndex);
            pileDrawnFrom = "the stock pile";

        } else {
            // Draw from discard pile
            cardDrawn = this.getDiscardPileCards().pop();
            pileDrawnFrom = "the discard pile";
        }

        player.getHandGroup().addCardAndSort(cardDrawn);

        // Output result
        System.out.print("\n" + player.nickname + " drew ");
        cardDrawn.outputCardToTerminalInColor();
        System.out.println(" from " + pileDrawnFrom + ".\n");
        player.outputGameStatus(this);
    }

    public void meld(Player player) {

        CardGroup meld = player.makeMeldChoice(this);

        if (meld.getGroup().isEmpty()) {
            System.out.println("\n" + player.nickname + " declined to meld.\n");

        } else {
            // Output action
            System.out.print("\n" + player.nickname + " melded ");
            meld.outputGroupOnOneLine();
        }
    }

    // TODO add lay off method, along with choice methods for both players

    // TODO add scoring methods
}
