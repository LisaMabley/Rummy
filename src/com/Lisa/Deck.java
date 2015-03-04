package com.Lisa;

import java.util.*;

// Created by lisa on 2/18/15.

public class Deck {

    private CardGroup stock = new CardGroup();
    private CardGroup discardPile = new CardGroup();

    public static char[] suits = { 9824, 9827, 9829, 9830 };

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

    public CardGroup getDiscardPile() {

        return discardPile;
    }

    public LinkedList<Card> getDiscardPileCards() {

        return discardPile.getGroup();
    }

    public void dealCards(int numCards, CardGroup group) {

        Random randomNumberGenerator = new Random();

        for (int x = 0; x < numCards; x ++) {
            int cardIndex = randomNumberGenerator.nextInt(this.getStockPile().size());
            group.addCardAndSort(this.getStockPile().remove(cardIndex));
        }
    }

    public void drawFromStockPile(CardGroup hand) {
        dealCards(1, hand);
    }

    public void drawFromDiscardPile(CardGroup hand) {
        hand.getGroup().add(this.getDiscardPile().getGroup().pop());
    }

    public void discard(Player player) {

        Card cardToDiscard = player.makeDiscardChoice(this);
        player.getHand().remove(cardToDiscard);
        this.getDiscardPileCards().push(cardToDiscard);

        // Output action
        System.out.print("\n" + player.nickname + " discarded ");
        cardToDiscard.outputCardToTerminalInColor();
    }

    public void outputGameStatus(HumanPlayer human, ComputerPlayer computer) {
        // TODO make list of players and iterate over it instead of repeating code

        // Output player cards
        System.out.println("\nYOUR HAND:");
        for (Card card : human.getHand()) {
            card.outputCardToTerminalInColor();
            ;
            if (card != human.getHand().getLast()) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nYOUR MELDS:");
        System.out.println("Runs");
        for (Card card : human.getRuns()) {
            card.outputCardToTerminalInColor();
        }
        System.out.println("Books");
        for (Card card : human.getBooks()) {
            card.outputCardToTerminalInColor();
        }

        // Display top card in discard pile
        System.out.println("\nDISCARD PILE:");
        if (this.discardPile.getGroup().isEmpty()) {
            this.dealCards(1, this.discardPile);
        }

        this.discardPile.getGroup().peek().outputCardToTerminalInColor();
        System.out.println("");

        // Output computer player's cards
        System.out.println("\nYOUR OPPONENT'S HAND:");
        for (Card card : computer.getHand()) {
            card.outputCardToTerminalInColor();
            ;
            if (card != computer.getHand().getLast()) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nYOUR OPPONENT'S MELDS:");
        System.out.println("Runs");
        for (Card card : computer.getRuns()) {
            card.outputCardToTerminalInColor();
        }

        System.out.println("Books");
        for (Card card : computer.getBooks()) {
            card.outputCardToTerminalInColor();
        }
    }
}
