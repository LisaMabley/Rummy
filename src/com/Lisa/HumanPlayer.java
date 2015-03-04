package com.Lisa;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by lisa on 3/3/15.
 */
public class HumanPlayer extends Player {

    // Constructor
    public HumanPlayer(String name) {
        this.nickname = name;
    }

    @Override
    public Card makeDiscardChoice(Deck deck) {
        System.out.println("DISCARD:\nWhich card would you like to discard to end your turn?");
        Card cardToDiscard = this.selectCardFromPlayerHand();
        return cardToDiscard;
    }

    public Card selectCardFromPlayerHand() {
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

            if (cardIndex < 0 || cardIndex > this.getHand().size()) {
                // User input an invalid integer
                continue;
            }
            break;
        }
        Card selectedCard = this.getHand().get(cardIndex);

        return selectedCard;
    }
}
