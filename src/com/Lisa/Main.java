package com.Lisa;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        // Initialize hands
        Hand playerHand = new Hand();
        Hand computerAiHand = new Hand();

        // Opening deal
        playerHand = newDeck.dealCards(10, playerHand);
        computerAiHand = newDeck.dealCards(10, computerAiHand);

        // Output player cards
        System.out.println("Your hand:");
        for (Card card : playerHand.getHand()) {
            System.out.println(card.getName());
        }

    }
}
