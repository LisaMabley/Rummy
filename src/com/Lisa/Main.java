package com.Lisa;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        Book humanBook = new Book();
        Book computerBook = new Book();

        Run humanRun = new Run();
        Run computerRun = new Run();

        // Initialize hands
        Player humanPlayer = new Player();
        Player computerAiPlayer = new Player();

        // Opening deal
        newDeck.dealCards(10, humanPlayer.getHandGroup());
        newDeck.dealCards(10, computerAiPlayer.getHandGroup());
        newDeck.dealCards(1, newDeck.getDiscardPile());

        outputGameStatus(humanPlayer.getHandGroup(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
        draw(humanPlayer, newDeck);
        meld(humanPlayer, newDeck);
        discard(humanPlayer, newDeck);

//        outputGameStatus(computerAiPlayer.getHand(), humanPlayer.getHand(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
//        draw(computerAiPlayer.getHand(), humanPlayer.getHand(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
//        meld(computerAiPlayer.getHand(), humanPlayer.getHand(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());
//        discard(computerAiPlayer.getHand(), humanPlayer.getHand(), newDeck, humanPlayer.getBooks(), humanPlayer.getRuns());

        // While whatever startRound()
        // When whatever calculateScore()
        // Display score
        // Ask if player wants to play again
    }

//    public static void startRound() {
//        // check if discard pile is empty
//        // interactWithHuman()
//        // aiStrategize()
//        // check if any player's hand is empty (they have won)
//    }

    public static void outputGameStatus(CardGroup handGroup, Deck newDeck,
                                        LinkedList<Card> books, LinkedList<Card> runs) {

        LinkedList<Card> handCards = handGroup.getGroup();

        // Output player cards
        System.out.println("\nYOUR HAND:");
        for (Card card : handCards) {
            card.outputCardToTerminalInColor();
            ;
            if (card != handCards.getLast()) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nMELD PILE:");
        for (Card card : runs) {
            card.outputCardToTerminalInColor();
            ;
        }
        for (Card card : books) {
            card.outputCardToTerminalInColor();
        }

        // Display top card in discard pile
        System.out.println("\n\nDISCARD PILE:");
        if (newDeck.getDiscardPile().getGroup().isEmpty()) {
            newDeck.dealCards(1, newDeck.getDiscardPile());
        }

        newDeck.getDiscardPile().getGroup().peek().outputCardToTerminalInColor();
    }

    public static void draw(Player player, Deck newDeck) {
        System.out.println("DRAW:");
        int drawFromPile = 0;

        // Get valid response from user
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to draw from the stock pile, or 2 to draw from the discard pile.");

            try {
                drawFromPile = scanner.nextInt();

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (drawFromPile != 1 && drawFromPile != 2) {
                // User input an invalid integer
                continue;
            }

            break;
        }

        // Draw card from selected pile
        switch (drawFromPile) {
            case 1:
                newDeck.drawFromStockPile(player.hand);
                break;
            case 2:
                newDeck.drawFromDiscardPile(player.hand);
                break;
        }
    }

    public static void meld(Player player, Deck newDeck) {
        System.out.println("MELD:\nWould you like to meld any runs or books?");
        LinkedList<Card> handCards = player.getHand();

        LinkedList<Card> meldList = new LinkedList<Card>();
        while (true) {
//            outputGameStatus(hand, newDeck, books, runs);
            Card cardToMeld = player.selectCardFromPlayerHand();
            meldList.add(cardToMeld);
            System.out.println("Any more? Enter 0 if you're done");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextInt() == 0) {
                cardToMeld.outputCardToTerminalInColor();
                break;
            }
            scanner.close();
            System.out.println("CURRENT MELD: ");
            for (Card card : meldList) {
                card.outputCardToTerminalInColor();
                if (card != meldList.getLast()) {
                    System.out.print(", ");
                }
            }
        }

        // Output action
        System.out.print("\nMelded \n");
    }

    public static void discard(Player player, Deck newDeck) {
        System.out.println("DISCARD:\nWhich card would you like to discard to end your turn?");
        Card cardToDiscard = player.selectCardFromPlayerHand();

        // Output action
        System.out.print("\nDiscarded ");
        cardToDiscard.outputCardToTerminalInColor();

        //  Discard selected card
        newDeck.discardCard(player, cardToDiscard);
    }
}

//    public static void calculateScore() {
//        // TODO
//    }
//}
