package com.Lisa;
import java.util.*;

// Created by lisa on 2/18/15.

public class Player {

    protected CardGroup hand = new CardGroup();
    protected CardGroup runs = new CardGroup();
    protected CardGroup books = new CardGroup();

    public static int selectCardFromPlayerHand() {
        // Returns a positive int indicating which card user wishes to select from their hand

        int cardIndex = -1;

        // Get valid response from user
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please use your number keypad to enter which card, as counted from the left.");

            try {
                cardIndex = scanner.nextInt();
                cardIndex --;

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (cardIndex < 0 || cardIndex > this.hand.size()) {
                // User input an invalid integer
                continue;
            }
            break;
        }
        return cardIndex;
    }

    // Getters
    public LinkedList<Card> getHand() {
        return hand.getGroup();
    }

    public CardGroup getHandGroup() {
        return hand;
    }

    public LinkedList<Card> getRuns() {
        return runs.getGroup();
    }

    public LinkedList<Card> getBooks() {
        return books.getGroup();
    }
}
