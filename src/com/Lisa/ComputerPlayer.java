package com.Lisa;

// Created by lisa on 3/1/15.

import java.util.Collections;
import java.util.LinkedList;

public class ComputerPlayer extends Player {

    // Constructor
    public ComputerPlayer(String name) {
        this.nickname = name;
    }

    public int makeDrawChoice(Deck newDeck) {
        // Evaluates options and chooses which pile to draw from

        Card discardOption = newDeck.getDiscardPileCards().peek();

        if (discardOption.canMeldWithAnyOtherCardInHand(this.getHandGroup()) ||
                discardOption.canAddToAnyMeldOnTable(newDeck)) {
            // If card from discard pile provides possible run or book options
            // with other cards in hand or melds on table, draw from discard pile
            return 2;
        }

        // If not, draw from stock pile
        return 1;
    }

    public CardGroup makeMeldChoice(Deck deck) {
        // Makes computer player's meld choice
        // TODO test thoroughly to be sure index out of bounds exceptions are really fixed

        CardGroup emptyGroup = new CardGroup();
        CardGroup possibleMeld = new CardGroup();

        // Look for runs
        // Would be nice to make sure this picked longest run, if more than one in hand
        // This just picks the first one it comes across
        int handSize = this.getHand().size();

        for (int x = 0; x < handSize - 2; x ++) {
            Card cardToCompare = this.getHand().get(x);
            Card nextCardInHand = this.getHand().get(x + 1);

            while (cardToCompare.isRunPartner(nextCardInHand)) {
                possibleMeld.addCard(cardToCompare);
                x ++;
                cardToCompare = this.getHand().get(x);

                if (cardToCompare == this.getHand().getLast()) {
                    break;

                } else {
                    nextCardInHand = this.getHand().get(x + 1);
                }
            }

            if (possibleMeld.getGroup().size() >= 2) {
                // We have a run!
                possibleMeld.addCardAndSort(cardToCompare);

                if (possibleMeld.isValidRun()) {
                    for (Card card : possibleMeld.getGroup()) {
                        this.hand.getGroup().remove(card);
                    }

                    Run newRun = new Run(possibleMeld);
                    deck.melds.add(newRun);
                    return newRun;
                }

            } else {
                possibleMeld.getGroup().clear();
            }
        }

        // Look for books
        Collections.sort(this.getHand());

        for (int x = 0; x < handSize - 2; x ++) {
            Card cardToCompare = this.getHand().get(x);
            Card nextCardInHand = this.getHand().get(x + 1);

            while (cardToCompare.isBookPartner(nextCardInHand)) {
                possibleMeld.addCard(cardToCompare);
                x++;
                cardToCompare = this.getHand().get(x);

                if (cardToCompare == this.getHand().getLast()) {
                    break;

                } else {
                    nextCardInHand = this.getHand().get(x + 1);
                }
            }

            if (possibleMeld.getGroup().size() >= 2) {
                // We have a book!
                possibleMeld.addCardAndSort(cardToCompare);
                if (possibleMeld.isValidBook()) {
                    for (Card card : possibleMeld.getGroup()) {
                        this.hand.getGroup().remove(card);
                    }

                    Book newBook = new Book(possibleMeld);
                    deck.melds.add(newBook);
                    return newBook;
                }

            } else {
                possibleMeld.getGroup().clear();
            }
        }
        return emptyGroup;
    }

    public void makeLayOffChoice(Deck deck) {

        for (Card card : this.getHand()) {
            if (card.canAddToAnyMeldOnTable(deck)) {
                System.out.println("This is a card I could be laying off right now: " + card.getName());
            }
        }

        System.out.println("But I don't want to lay off and you can't make me.");
    }

    public void outputHand() {
        // Output computer player cards
        System.out.println("\nYOUR OPPONENT'S HAND:");
        this.hand.outputGroupOnOneLine();
    }

    public Card makeDiscardChoice(Deck deck) {
        // Evaluates computer player's options and chooses which card to discard

        Card cardToDiscard = null;

        for (Card card : this.getHand()) {
            if (card.canDiscardThisTurn() && !card.canAddToAnyMeldOnTable(deck) &&
                    !card.canMeldWithAnyOtherCardInHand(this.getHandGroup())) {
                cardToDiscard = card;
            }
        }

        if (cardToDiscard == null) {
            cardToDiscard = this.getHand().getFirst();
        }

        this.getHand().remove(cardToDiscard);
        deck.getDiscardPileCards().push(cardToDiscard);
        this.isHandEmpty();
        return cardToDiscard;
    }
}

