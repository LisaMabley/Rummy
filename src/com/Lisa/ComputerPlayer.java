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
                discardOption.canLayoffToTableMeldAtIndex(newDeck) >=0) {
            // If card from discard pile provides possible run or book options
            // with other cards in hand or melds on table, draw from discard pile
            return 2;
        }

        // If not, draw from stock pile
        return 1;
    }

    public CardGroup makeMeldChoice(Deck deck) {
        // Makes computer player's meld choice

        CardGroup emptyGroup = new CardGroup();
        CardGroup possibleMeld = new CardGroup();

        // Look for runs
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
                    this.isHandEmpty();
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
                    this.isHandEmpty();
                    return newBook;
                }

            } else {
                possibleMeld.getGroup().clear();
            }
        }
        return emptyGroup;
    }

    public void makeLayOffChoice(Deck deck) {
        // Evaluates computer player's options and chooses cards to lay off

        int indexOfSelectedMeld = -1;
        LinkedList<Card> cardsLaidOff = new LinkedList<Card>();

        for (Card card : this.getHand()) {
            indexOfSelectedMeld = card.canLayoffToTableMeldAtIndex(deck);
            if (indexOfSelectedMeld >= 0) {
                deck.melds.get(indexOfSelectedMeld).addCardAndSort(card);
                deck.melds.get(indexOfSelectedMeld).outputGroupOnOneLine();
                cardsLaidOff.add(card);
            }
        }

        for (Card laidOffCard : cardsLaidOff) {
            this.getHand().remove(laidOffCard);
        }
        this.isHandEmpty();
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
            // Discard first card that comes up that wasn't drawn from the discard pile this turn,
            // doesn't have any potential melds in player's hand, or layoff options on table
            if (card.canDiscardThisTurn() && card.canLayoffToTableMeldAtIndex(deck) == -1 &&
                    !card.canMeldWithAnyOtherCardInHand(this.getHandGroup())) {
                cardToDiscard = card;
            }
        }

        // If no such card exists, discard first card
        if (cardToDiscard == null) {
            cardToDiscard = this.getHand().getFirst();
        }

        this.getHand().remove(cardToDiscard);
        deck.getDiscardPileCards().push(cardToDiscard);
        this.isHandEmpty();
        return cardToDiscard;
    }
}

